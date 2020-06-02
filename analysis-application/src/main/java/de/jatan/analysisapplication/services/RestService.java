package de.jatan.analysisapplication.services;

import java.util.Collections;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.jatan.analysisapplication.Domain.Model.GithubOrganization;
import de.jatan.analysisapplication.controller.GithubController;
import de.jatan.analysisapplication.controller.DTO.OrganizationInformationDTO;
@Service
public class RestService {


    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();

    }

public GithubOrganization getOrganization2(){
    String url = "http://localhost:8080/github/organizations?organizationName=OpenDevSpace";
    HttpHeaders headers = new HttpHeaders();

    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    // set custom header
    headers.set("x-request-source", "desktop");

    // build the request
    HttpEntity request = new HttpEntity(headers);

    ResponseEntity<GithubOrganization> response = this.restTemplate.exchange(url, HttpMethod.GET, request, GithubOrganization.class, 1);
    System.out.println("Es funktioniert");
    return response.getBody();

}

 
}