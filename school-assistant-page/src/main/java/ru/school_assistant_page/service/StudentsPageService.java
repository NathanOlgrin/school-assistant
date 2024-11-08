package ru.school_assistant_page.service;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.school_assistant_page.client.StudentsResponse;
import ru.school_assistant_page.controller.dto.StudentsPageDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class StudentsPageService {

    private final DiscoveryClient discoveryClient;
    public StudentsPageService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    private RestClient restClient(){
        List<ServiceInstance> instances = discoveryClient.getInstances("SCHOOL-ASSISTANT-REST");
        int instanceCount = instances.size();
        int instanceIndex = ThreadLocalRandom.current().nextInt(0,instanceCount);
        ServiceInstance serviceInstance = instances.get(instanceIndex);

        String uri = "http://"+serviceInstance.getHost() + ":" + serviceInstance.getPort();
        return RestClient.builder().baseUrl(uri).defaultHeaders(httpHeaders -> {httpHeaders.setBasicAuth("user", "user");}).build();
    }

    public Optional<StudentsPageDto> findById(Long id){

        try {
            StudentsResponse studentsResponse = restClient().get().uri("/students/" + id).retrieve().body(StudentsResponse.class);

            StudentsPageDto studentsPageDto = new StudentsPageDto();
            studentsPageDto.setId(String.valueOf(studentsResponse.getId()));
            studentsPageDto.setFirstName(studentsResponse.getFirstName());
            studentsPageDto.setMiddleName(studentsResponse.getMiddleName());
            studentsPageDto.setLastName(studentsResponse.getLastName());
            studentsPageDto.setBirthday(String.valueOf(studentsResponse.getBirthday()));
            studentsPageDto.setNumberOfClass(String.valueOf(studentsResponse.getNumberOfClass()));

            return Optional.of(studentsPageDto);
        } catch (HttpClientErrorException.NotFound e){
            return Optional.empty();
        }
    }

    public List<StudentsPageDto> findAll(){
        List<StudentsResponse> studentsResponses = null;

        int attempts = 5;
        while(attempts -- > 0){
            try{
                studentsResponses = restClient().get()
                        .uri("/students")
                        .retrieve()
                        .body(new ParameterizedTypeReference<List<StudentsResponse>>() {});
                break;
            } catch (HttpClientErrorException e) {
                // ignore
            }
        }

        if(studentsResponses == null){
            throw new RuntimeException("oops");
        }

        List<StudentsPageDto> result = new ArrayList<>();

        for (StudentsResponse studentsResponse: studentsResponses) {

            StudentsPageDto studentsPageDto = new StudentsPageDto();
            studentsPageDto.setId(String.valueOf(studentsResponse.getId()));
            studentsPageDto.setFirstName(studentsResponse.getFirstName());
            studentsPageDto.setMiddleName(studentsResponse.getMiddleName());
            studentsPageDto.setLastName(studentsResponse.getLastName());
            studentsPageDto.setBirthday(String.valueOf(studentsResponse.getBirthday()));
            studentsPageDto.setNumberOfClass(String.valueOf(studentsResponse.getNumberOfClass()));

            result.add(studentsPageDto);
        }

        return result;
    }
}
