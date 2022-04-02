package com.example.cochceszsewpisz.Event;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = AUTO)
    public UUID id;

    @NonNull
    public String name;

    @NonNull
    public LocalDate date;

    @NonNull
    public String place;

    @NonNull
    public String description;
}
