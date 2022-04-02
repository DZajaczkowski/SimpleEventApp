package com.example.cochceszsewpisz.Event;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/event")
public class EventController {

    private final EventRepository eventRepository;

    @PostMapping
    @ResponseStatus(CREATED)
    public Event createEvent(@RequestBody EventData eventData) {
        return eventRepository.save(new Event(eventData.name(), eventData.date(), eventData.place(), eventData.description()));
    }

    @GetMapping("/{id}")
    public Event showEventById(@PathVariable UUID id) {
        return eventRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);
    }

    @GetMapping
    public List<Event> showEventList() {
        return eventRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteEvent(@PathVariable UUID id) {
        eventRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Event editEventById(@PathVariable UUID id, @RequestBody EventData eventData) {
        Event event = eventRepository.getById(id);
        event.setName(eventData.name());
        event.setDate(eventData.date());
        event.setPlace(eventData.place());
        event.setDescription(eventData.description());
        return eventRepository.save(event);
    }
}
