package edu.miu.WAA_Project.service.impl;

import edu.miu.WAA_Project.entity.JobAdvertisement;
import edu.miu.WAA_Project.entity.Student;
import edu.miu.WAA_Project.repository.JobAdvertisementRepo;
import edu.miu.WAA_Project.repository.StudentRepository;
import edu.miu.WAA_Project.service.EchartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EchartServiceImpl implements EchartService {

    private final StudentRepository studentRepository;
    private final JobAdvertisementRepo jobAdvertisementRepo;
    @Override
    public List<String> getStudentByCity() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        List<String> city = students.stream().map(s->s.getAddress().getCity()).toList();
        return city;
    }

    @Override
    public List<String> getStudentByState() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        List<String> state = students.stream().map(s->s.getAddress().getState()).toList();
        return state;
    }

    @Override
    public List<String> getJobByCity() {
        List<JobAdvertisement> jobAdvertisements = (List<JobAdvertisement>) jobAdvertisementRepo.findAll();
        List<String> jobByCity = jobAdvertisements.stream().map(jobAdv->jobAdv.getAddress().getCity()).toList();
        return jobByCity;
    }
    @Override
    public List<String> getJobByState() {
        List<JobAdvertisement> jobAdvertisements = (List<JobAdvertisement>) jobAdvertisementRepo.findAll();
        List<String> jobByState = jobAdvertisements.stream().map(jobAdv->jobAdv.getAddress().getState()).toList();
        return jobByState;
    }
    @Override
    public List<String> getJobByTags() {
        List<JobAdvertisement> jobAdvertisements = (List<JobAdvertisement>) jobAdvertisementRepo.findAll();
        List<String> jobByTag = jobAdvertisements
                .stream()
                .flatMap(adv->adv.getTag()
                        .stream()
                        .map(t->t.getTag()
                        ))
                .toList();

        return jobByTag;

    }
}
