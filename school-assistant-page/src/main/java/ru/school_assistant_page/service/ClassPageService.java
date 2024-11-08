package ru.school_assistant_page.service;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.school_assistant_page.client.StudentsResponse;
import ru.school_assistant_page.client.TeachersResponse;
import ru.school_assistant_page.controller.dto.ClassPageDto;
import ru.school_assistant_page.controller.dto.StudentsPageDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ClassPageService {

    private final DiscoveryClient discoveryClient;
    public ClassPageService(DiscoveryClient discoveryClient) {
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

    public Optional<ClassPageDto> findByNumberOfClass(Long numberOfClass){

        try {
            ClassPageDto classPageDto = new ClassPageDto();
            classPageDto.setNumberOfClass(String.valueOf(numberOfClass));

            TeachersResponse teachersResponse = restClient().get()
                    .uri("/teachers/numberOfClass="+numberOfClass)
                    .retrieve()
                    .body(new ParameterizedTypeReference<TeachersResponse>() {});

            classPageDto.setTeachersName(teachersResponse.getFirstName() +" " + teachersResponse.getMiddleName().substring(0,1) + "." + teachersResponse.getLastName().substring(0,1) + ".");

            List<StudentsResponse> studentsResponses = null;

            int attempts = 5;
            while(attempts -- > 0){
                try{
                    studentsResponses = restClient().get()
                            .uri("/students/numberOfClass="+numberOfClass)
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

            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (StudentsResponse studentsResponse: studentsResponses) {

                sb.append(studentsResponse.getFirstName() +" " + studentsResponse.getMiddleName().substring(0,1) + "." + studentsResponse.getLastName().substring(0,1) + ".\n");
            }

            classPageDto.setStudentsName(sb.toString());

            return Optional.of(classPageDto);
        } catch (HttpClientErrorException.NotFound e){
            return Optional.empty();
        }

    }

}
