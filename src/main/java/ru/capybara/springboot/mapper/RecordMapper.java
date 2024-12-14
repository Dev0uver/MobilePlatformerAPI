package ru.capybara.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.capybara.springboot.dto.RecordDto;
import ru.capybara.springboot.model.Record;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    @Mapping(target = "user.id", source = "userId")
    Record toEntity(RecordDto recordDto);
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "nickname", source = "user.nickname")
    RecordDto toDto(Record record);
}
