package com.example.cochceszsewpisz.LocalUser;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class LocalUser {

    @Id
    @GeneratedValue(strategy= AUTO)
    public UUID id;

    @NonNull
    public String name;

    @NonNull
    public String email;
}
