package ru.capybara.springboot.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecordDto {
    Long id;
    int score;
    LocalTime time;
    int level;
    Long userId;
    String nickname;
}