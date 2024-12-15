package ru.capybara.springboot.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "score", nullable = false)
    int score;
    @Column(name = "time", nullable = false)
    Time time;
    @Column(name = "level", nullable = false)
    int level;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    User user;
}
