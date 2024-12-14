package ru.capybara.springboot.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import ru.capybara.springboot.dto.RecordDto;
import ru.capybara.springboot.mapper.RecordMapper;
import ru.capybara.springboot.model.Record;
import ru.capybara.springboot.model.User;
import ru.capybara.springboot.repository.RecordRepository;
import ru.capybara.springboot.service.RecordService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecordServiceImpl implements RecordService {
    RecordRepository recordRepository;
    RecordMapper recordMapper;

    @Override
    public RecordDto saveResult(RecordDto recordDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recordDto.setUserId(user.getId());
        recordDto.setNickname(user.getNickname());
        Optional<Record> record = recordRepository.findByUserIdAndLevel(user.getId(), recordDto.getLevel());
        if (record.isEmpty()) {
            return recordMapper.toDto(recordRepository.save(recordMapper.toEntity(recordDto)));
        }
        recordDto.setId(record.get().getId());
        if (record.get().getTime().isBefore(recordDto.getTime())) {
            return recordDto;
        }
        if (record.get().getTime().equals(recordDto.getTime())) {
            if (record.get().getScore() < recordDto.getScore()) {
                return recordMapper.toDto(recordRepository.save(recordMapper.toEntity(recordDto)));
            }
            record.get().setScore(recordDto.getScore());
            return recordMapper.toDto(record.get());
        }
        record.get().setScore(recordDto.getScore());
        record.get().setTime(recordDto.getTime());
        recordRepository.save(record.get());
        return recordMapper.toDto(record.get());
    }

    @Override
    public List<RecordDto> getBestRecords(int level) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "time"));
        Page<Record> recordsPage = recordRepository.findAllByLevel(level, pageable);
        return recordsPage.getContent().stream().map(recordMapper::toDto).collect(Collectors.toList());
    }
}
