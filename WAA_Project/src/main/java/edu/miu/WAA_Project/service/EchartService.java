package edu.miu.WAA_Project.service;

import java.util.List;

public interface EchartService {
    List<String> getStudentByCity();
    List<String> getStudentByState();
    List<String> getJobByCity();
    List<String> getJobByState();
    public List<String> getJobByTags();

}
