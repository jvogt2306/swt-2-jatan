package de.jatan.analysisapplication.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import de.jatan.analysisapplication.Domain.Model.SonarQubeProject;
import de.jatan.analysisapplication.Domain.Model.SonarQubeProjectResponse;
import de.jatan.analysisapplication.Domain.Model.SonarQubeProjectWebhook;
import de.jatan.analysisapplication.config.GlobalConfiguration;

@Service
@Configuration
@PropertySource("classpath:application.properties")
public class SonarQubeService {
  private static final String APPLICATIONPATH = System.getProperty("user.dir");
  private static final String REPOSITORYPATH = "/src/main/resources/repositories/";
  public static final String REPOSITORYFOLDERABSOLUTE = APPLICATIONPATH + REPOSITORYPATH;
  private final RestTemplate restTemplate;

  public SonarQubeService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder
        .basicAuthentication(GlobalConfiguration.SonarUser, GlobalConfiguration.SonarPassword).build();
  }

  public boolean scanRepository(String projectName, String language) {
    try {
      String projectPath = REPOSITORYFOLDERABSOLUTE + projectName;
      createSonarPropertiesFile(REPOSITORYFOLDERABSOLUTE, projectName, language);
      scanRespositoryInSonarqube(projectPath);
      removeRepositoryFromPath(projectPath);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
    return true;
  }

  public boolean createSonarQubeProject(String projectName) {
    if (checkIfProjectExist(projectName)) {
      return true;
    }

    String createSonarProjectEndpoint = GlobalConfiguration.SonarAdress + "/api/projects/create";
    String project = projectName;
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(createSonarProjectEndpoint)
        .queryParam("name", projectName).queryParam("project", project);
    ResponseEntity<Object> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null,
        Object.class);
    return (responseEntity.getStatusCode() == HttpStatus.OK);
  }

  public boolean updateWebhookPropertieSonarQubeProject(String projectName) {
    if (checkIfWebhookExist(projectName))
      return true;
    String createSonarWebhookEndpoint = GlobalConfiguration.SonarAdress + "/api/webhooks/create";
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(createSonarWebhookEndpoint)
        .queryParam("name", "Sonarqube-Spring").queryParam("project", projectName)
        .queryParam("url", GlobalConfiguration.JatanSonarHook);
    ResponseEntity<Object> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null,
        Object.class);
    return (responseEntity.getStatusCode() == HttpStatus.OK);
  }

  public boolean scanRespositoryInSonarqube(String path) throws InterruptedException, IOException {
    ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.redirectErrorStream(true);
    processBuilder.directory(new File(path));
    processBuilder.command(GlobalConfiguration.SonarScannerPath, "-Dproject.settings=./sonar-project.properties",
        "-Dsonar.host.url=" + GlobalConfiguration.SonarAdress, "-Dsonar.login=" + GlobalConfiguration.SonarLogin);
    processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
    executeProcesses(processBuilder);
    return true;
  }

  public boolean removeRepositoryFromSonarQube(SonarQubeProject project) {
    String deleteSonarProjectEndpoint = GlobalConfiguration.SonarAdress + "/api/projects/delete";
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(deleteSonarProjectEndpoint).queryParam("project",
        project.getKey());
    ResponseEntity<Object> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null,
        Object.class);
    return (responseEntity.getStatusCode() == HttpStatus.OK);
  }

  public boolean removeRepositoryFromPath(String path) {
    return FileSystemUtils.deleteRecursively(new File(path));
  }

  public boolean createSonarPropertiesFile(String path, String projectName, String language) throws IOException {
    File sonarPropertiesFile = new File(path + "/" + projectName + "/" + "sonar-project.properties");
    BufferedWriter writer = new BufferedWriter(new FileWriter(sonarPropertiesFile, true));
    writeSonarProperties(writer, projectName, language);
    return true;
  }

  public boolean checkIfProjectExist(String projectName) {
    String searchSonarProjectEndpoint = GlobalConfiguration.SonarAdress + "/api/projects/search";
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(searchSonarProjectEndpoint)
        .queryParam("projects", projectName);
    ResponseEntity<SonarQubeProjectResponse> responseEntity = restTemplate.exchange(uriBuilder.toUriString(),
        HttpMethod.POST, null, SonarQubeProjectResponse.class);
    return (responseEntity.getBody().getComponents().length != 0);
  }

  public boolean checkIfWebhookExist(String projectName) {
    String searchSonarProjectEndpoint = GlobalConfiguration.SonarAdress + "/api/webhooks/list";
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(searchSonarProjectEndpoint).queryParam("project",
        projectName);
    ResponseEntity<SonarQubeProjectWebhook> responseEntity = restTemplate.exchange(uriBuilder.toUriString(),
        HttpMethod.POST, null, SonarQubeProjectWebhook.class);
    return (responseEntity.getBody().getWebhooks().length != 0);
  }

  public boolean writeSonarProperties(BufferedWriter writer, String projectName, String language) throws IOException {

    if (language.equals("Java")) {
      writer.write("sonar.java.binaries=.");
      writer.newLine();
    }

    writer.write("sonar.projectKey=" + projectName);
    writer.newLine();
    writer.close();
    return true;
  }

  public boolean executeProcesses(ProcessBuilder processBuilder) throws IOException, InterruptedException {
    Process process = processBuilder.start();
    process.waitFor();
    process.destroy();
    return true;
  }
}
