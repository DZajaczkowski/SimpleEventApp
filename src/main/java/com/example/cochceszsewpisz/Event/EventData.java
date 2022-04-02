package com.example.cochceszsewpisz.Event;

import java.time.LocalDate;

public record EventData(
    String name,
    LocalDate date,
    String place,
    String description
) {
}
