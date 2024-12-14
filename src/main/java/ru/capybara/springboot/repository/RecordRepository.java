package ru.capybara.springboot.repository;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.capybara.springboot.model.Record;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    Page<Record> findAllByLevel(int level, Pageable pageable);
    Optional<Record> findByUserIdAndLevel(long userId, int level);
}

