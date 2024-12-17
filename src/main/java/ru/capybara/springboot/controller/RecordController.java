package ru.capybara.springboot.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.capybara.springboot.dto.RecordDto;
import ru.capybara.springboot.exception.EmailIsBusyException;
import ru.capybara.springboot.model.User;
import ru.capybara.springboot.service.RecordService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/record") //http://localhost:8080/api/record
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecordController {
    RecordService recordService;
    @GetMapping
    public ResponseEntity<List<RecordDto>> getBestRecords(@RequestParam Integer level) {
        return ResponseEntity.ok(recordService.getBestRecords(level));
    }

    @PostMapping("/save")
    public ResponseEntity<RecordDto> saveResult(@RequestBody RecordDto recordDto) {
        return ResponseEntity.ok(recordService.saveResult(recordDto));
    }
}
