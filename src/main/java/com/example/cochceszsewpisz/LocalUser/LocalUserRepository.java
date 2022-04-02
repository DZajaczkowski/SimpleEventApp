package com.example.cochceszsewpisz.LocalUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocalUserRepository extends JpaRepository<LocalUser, UUID> {
}
