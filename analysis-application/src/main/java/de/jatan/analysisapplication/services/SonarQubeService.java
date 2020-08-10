package de.jatan.analysisapplication.services;

import java.io.*;

import org.springframework.boot.web.client.RestTemplateBuilder;
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

@Service
public class SonarQubeService {

  private final static String applicationPath = System.getProperty("user.dir"); // System.getProperty("user.dir");
  private final static String repositoryPath = "/src/main/resources/repositories/"; /// "/src/main/resources/repositories/"
  private final static String analysisSonarqubeHook = "http://192.168.1.32:8080/sonarqube/hook";
  private final static String sonarUser = "admin";
  private final static String sonarPassword = "admin";
  private final static String sonarURL = "http://192.168.1.32:9000";
  private final RestTemplate restTemplate;

  public final static String repositoryFolderAbsolute = applicationPath + repositoryPath;

  public SonarQubeService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.basicAuthentication(sonarUser, sonarPassword).build();
  }

  public boolean scanRepository(String projectName, String language) {
    try {
      String projectPath = repositoryFolderAbsolute + projectName;
      createSonarPropertiesFile(repositoryFolderAbsolute, projectName, language);
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
    if (checkIfProjectExist(projectName))
      return true;
    String createSonarProjectEndpoint = sonarURL + "api/projects/create";
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(createSonarProjectEndpoint)
        .queryParam("name", projectName).queryParam("project", projectName);
    ResponseEntity<Object> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null,
        Object.class);
    return (responseEntity.getStatusCode() == HttpStatus.OK) ? true : false;
  }

  public boolean updateWebhookPropertieSonarQubeProject(String projectName) {
    if (checkIfWebhookExist(projectName))
      return true;
    String createSonarWebhookEndpoint = sonarURL + "/api/webhooks/create";
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(createSonarWebhookEndpoint)
        .queryParam("name", "Sonarqube-Spring").queryParam("project", projectName)
        .queryParam("url", analysisSonarqubeHook);
    ResponseEntity<Object> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null,
        Object.class);
    return (responseEntity.getStatusCode() == HttpStatus.OK) ? true : false;
  }

  public boolean scanRespositoryInSonarqube(String path) throws InterruptedException, IOException {
    ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.redirectErrorStream(true);
    processBuilder.directory(new File(path));
    processBuilder.command("sonar-scanner", "-Dproject.settings=./sonar-project.properties",
        "-Dsonar.host.url=http://192.168.1.32:9000", "-Dsonar.login=cabdd35cbe9411b527a70d15c261b68055100c8d");
    processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
    executeProcesses(processBuilder);
    return true;
  }

  public boolean removeRepositoryFromSonarQube(SonarQubeProject project) {
    String deleteSonarProjectEndpoint = sonarURL + "/api/projects/delete";
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(deleteSonarProjectEndpoint).queryParam("project",
        project.getKey());
    ResponseEntity<Object> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null,
        Object.class);
    return (responseEntity.getStatusCode() == HttpStatus.OK) || false;
  }

  public boolean removeRepositoryFromPath(String path) throws IOException {
    return FileSystemUtils.deleteRecursively(new File(path));
  }

  public boolean createSonarPropertiesFile(String path, String projectName, String language) throws IOException {
    File sonarPropertiesFile = new File(path + "/" + projectName + "/" + "sonar-project.properties");
    BufferedWriter writer = new BufferedWriter(new FileWriter(sonarPropertiesFile, true));
    writeSonarProperties(writer, projectName, language);
    return true;
  }

  public boolean checkIfProjectExist(String projectName) {
    String searchSonarProjectEndpoint = sonarURL + "api/projects/search";
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(searchSonarProjectEndpoint)
        .queryParam("projects", projectName);
    ResponseEntity<SonarQubeProjectResponse> responseEntity = restTemplate.exchange(uriBuilder.toUriString(),
        HttpMethod.POST, null, SonarQubeProjectResponse.class);
    return (responseEntity.getBody().getComponents().length == 0) ? false : true;
  }

  public boolean checkIfWebhookExist(String projectName) {
    String searchSonarProjectEndpoint = sonarURL + "api/webhooks/list";
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(searchSonarProjectEndpoint).queryParam("project",
        projectName);
    ResponseEntity<SonarQubeProjectWebhook> responseEntity = restTemplate.exchange(uriBuilder.toUriString(),
        HttpMethod.POST, null, SonarQubeProjectWebhook.class);
    return (responseEntity.getBody().getWebhooks().length == 0) ? false : true;
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
