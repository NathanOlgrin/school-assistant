package ru.school_assistant_page.service;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.school_assistant_page.client.StudentsResponse;
import ru.school_assistant_page.client.TeachersResponse;
import ru.school_assistant_page.controller.dto.TeachersPageDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TeachersPageService {

    private final DiscoveryClient discoveryClient;
    public TeachersPageService(DiscoveryClient discoveryClient) {
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

    public Optional<TeachersPageDto> findById(Long id){

        try {
            TeachersResponse teachersResponse = restClient().get().uri("/teachers/"+id).retrieve().body(TeachersResponse.class);

            TeachersPageDto teachersPageDto = new TeachersPageDto();
            teachersPageDto.setId(String.valueOf(teachersResponse.getId()));
            teachersPageDto.setFirstName(teachersResponse.getFirstName());
            teachersPageDto.setMiddleName(teachersResponse.getMiddleName());
            teachersPageDto.setLastName(teachersResponse.getLastName());
            teachersPageDto.setBirthday(String.valueOf(teachersResponse.getBirthday()));
            teachersPageDto.setNumberOfClass(String.valueOf(teachersResponse.getNumberOfClass()));
            teachersPageDto.setCategory(teachersResponse.getCategory().toString());

            return Optional.of(teachersPageDto);
        } catch (HttpClientErrorException e){
            return Optional.empty();
        }
    }

    public List<TeachersPageDto> findAll(){
         List<TeachersResponse> teachersResponses = null;

        int attempts = 5;
        while(attempts -- > 0){
            try{
                teachersResponses = restClient().get()
                        .uri("/teachers")
                        .retrieve()
                        .body(new ParameterizedTypeReference<List<TeachersResponse>>() {});
                break;
            } catch (HttpClientErrorException e) {
                // ignore
            }
        }

        if(teachersResponses == null){
            throw new RuntimeException("oops");
        }

        List<TeachersPageDto> result = new ArrayList<>();

        for (TeachersResponse teachersResponse: teachersResponses) {

            TeachersPageDto teachersPageDto = new TeachersPageDto();
            teachersPageDto.setId(String.valueOf(teachersResponse.getId()));
            teachersPageDto.setFirstName(teachersResponse.getFirstName());
            teachersPageDto.setMiddleName(teachersResponse.getMiddleName());
            teachersPageDto.setLastName(teachersResponse.getLastName());
            teachersPageDto.setBirthday(String.valueOf(teachersResponse.getBirthday()));
            teachersPageDto.setNumberOfClass(String.valueOf(teachersResponse.getNumberOfClass()));
            teachersPageDto.setCategory(teachersResponse.getCategory().toString());

            result.add(teachersPageDto);

        }

        return result;
    }
}
