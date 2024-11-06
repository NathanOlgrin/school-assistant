package ru.school_assistant_page.service;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.school_assistant_page.client.DayOfWeek;
import ru.school_assistant_page.client.LessonsResponse;
import ru.school_assistant_page.client.TeachersResponse;
import ru.school_assistant_page.controller.dto.LessonsPageDto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class LessonsPageService {

    private final DiscoveryClient discoveryClient;

    public LessonsPageService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    private RestClient restClient(){
        List<ServiceInstance> instances = discoveryClient.getInstances("SCHOOL-ASSISTANT-REST");
        int instanceCount = instances.size();
        int instanceIndex = ThreadLocalRandom.current().nextInt(0,instanceCount);
        ServiceInstance serviceInstance = instances.get(instanceIndex);

        String uri = "http://"+serviceInstance.getHost() + ":" + serviceInstance.getPort();
        return RestClient.create(uri);
    }

    public Optional<LessonsPageDto> findById(long id){
        try{


            LessonsResponse lesson = restClient().get().uri("/lessons/"+id).retrieve().body(LessonsResponse.class);

            LessonsPageDto lessonsPageDto = new LessonsPageDto();

            lessonsPageDto.setId(String.valueOf(lesson.getId()));
            lessonsPageDto.setLessonsName(lesson.getLessonsName());
            lessonsPageDto.setLessonStart(String.valueOf(lesson.getLessonStart()));
            lessonsPageDto.setLessonEnd(String.valueOf(lesson.getLessonEnd()));
            lessonsPageDto.setDaysOfWeek(lesson.getDaysOfWeek().toString());
            lessonsPageDto.setNumberOfClass(String.valueOf(lesson.getNumberOfClass()));

            TeachersResponse teachersResponse = restClient().get().uri("/teachers/"+lesson.getTeacherId()).retrieve().body(TeachersResponse.class);

            lessonsPageDto.setTeacherName(teachersResponse.getFirstName() +" " + teachersResponse.getMiddleName().substring(0,1) + "." + teachersResponse.getLastName().substring(0,1) + ".");


            return Optional.of(lessonsPageDto);
        } catch (HttpClientErrorException.NotFound e){
            return Optional.empty();
        }
    }

    public List<LessonsPageDto> findAll(){
        List<LessonsResponse> lessonsResponses = null;

        int attempts = 5;
        while(attempts -- > 0){
            try{
                lessonsResponses = restClient().get()
                        .uri("/lessons")
                        .retrieve()
                        .body(new ParameterizedTypeReference<List<LessonsResponse>>() {});
                break;
            } catch (HttpClientErrorException e) {
                // ignore
            }
        }

        if(lessonsResponses == null){
            throw new RuntimeException("oops");
        }

        List<LessonsPageDto> result = new ArrayList<>();

        for(LessonsResponse lesson : lessonsResponses){

            LessonsPageDto lessonsPageDto = new LessonsPageDto();

            lessonsPageDto.setId(String.valueOf(lesson.getId()));
            lessonsPageDto.setLessonsName(lesson.getLessonsName());
            lessonsPageDto.setLessonStart(String.valueOf(lesson.getLessonStart()));
            lessonsPageDto.setLessonEnd(String.valueOf(lesson.getLessonEnd()));
            lessonsPageDto.setDaysOfWeek(lesson.getDaysOfWeek().toString());
            lessonsPageDto.setNumberOfClass(String.valueOf(lesson.getNumberOfClass()));

            TeachersResponse teachersResponse = restClient().get().uri("/teachers/"+lesson.getTeacherId()).retrieve().body(TeachersResponse.class);

            lessonsPageDto.setTeacherName(teachersResponse.getFirstName() +" " + teachersResponse.getMiddleName().substring(0,1) + "." + teachersResponse.getLastName().substring(0,1) + ".");


            result.add(lessonsPageDto);
        }

        return result;
    }
}
