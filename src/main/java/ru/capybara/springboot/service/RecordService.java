package ru.capybara.springboot.service;

import ru.capybara.springboot.dto.RecordDto;

import java.util.List;

public interface RecordService {
    RecordDto saveResult(RecordDto recordDto);
    List<RecordDto> getBestRecords(int level);
}
