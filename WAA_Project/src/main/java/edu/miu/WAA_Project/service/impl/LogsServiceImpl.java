package edu.miu.WAA_Project.service.impl;

import edu.miu.WAA_Project.entity.Logs;
import edu.miu.WAA_Project.entity.dto.entityDto.logsDto.LogsBasicDto;
import edu.miu.WAA_Project.repository.LogsRepo;
import edu.miu.WAA_Project.service.LogsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogsServiceImpl implements LogsService {
    private final LogsRepo logsRepo;
    private final ModelMapper modelMapper;

    public LogsServiceImpl(LogsRepo logsRepo, ModelMapper modelMapper) {
        this.logsRepo = logsRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public LogsBasicDto getById(int id) {
        Logs logs= logsRepo.findById(id).get();
        return modelMapper.map(logs,LogsBasicDto.class);
    }

    @Override
    public List<LogsBasicDto> getAll() {
        List<Logs> logsList = (List<Logs>) logsRepo.findAll();
        return logsList
                .stream()
                .map(
                        logs->modelMapper
                                .map(logs,LogsBasicDto.class))
                .toList();
    }

    @Override
    public void save(LogsBasicDto logsDto) {
        Logs logs = modelMapper.map(logsDto,Logs.class);
        logsRepo.save(logs);
    }

    @Override
    public void update(int id, LogsBasicDto logsDto) {

        Logs logs = modelMapper.map(logsDto,Logs.class);

        Logs oldLogs = logsRepo.findById(id).get();
        logsRepo.delete(oldLogs);
        logs.setId(id);
        logsRepo.save(logs);
    }

    @Override
    public void delete(int id) {
        logsRepo.deleteById(id);
    }
}
