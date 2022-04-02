package com.example.cochceszsewpisz.LocalUser;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class LocalUserController {

    private final LocalUserRepository localUserRepository;

    @PostMapping
    @ResponseStatus(CREATED)
    public LocalUser createLocalUser(@RequestBody LocalUserData localUserData) {
        return localUserRepository.save(new LocalUser(localUserData.name(), localUserData.email()));
    }

    @GetMapping("/{id}")
    public LocalUser showLocalUserById(@PathVariable UUID id) {
        return localUserRepository.findById(id)
                .orElseThrow(LocalUserNotFoundException::new);
    }

    @GetMapping
    public List<LocalUser> showLocalUserList() {
        return localUserRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteLocalUser(@PathVariable UUID id) {
        localUserRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(CREATED)
    public LocalUser editLocalUserById(@PathVariable UUID id, @RequestBody LocalUserData localUserData) {
        LocalUser localUser = localUserRepository.getById(id);
        localUser.setName(localUserData.name());
        localUser.setEmail(localUserData.email());
        return localUserRepository.save(localUser);
    }
}
