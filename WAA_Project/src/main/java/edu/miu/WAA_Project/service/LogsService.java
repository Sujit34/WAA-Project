package edu.miu.WAA_Project.service;

import edu.miu.WAA_Project.entity.dto.entityDto.logsDto.LogsBasicDto;

import java.util.List;

public interface LogsService {
    LogsBasicDto getById(int id);
    List<LogsBasicDto> getAll();
    void save(LogsBasicDto logsDto);
    void update(int id, LogsBasicDto logsDto);
    void delete(int id);
}
