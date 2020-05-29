package de.jatan.analysisapplication.services;

import java.io.*;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SonarQubeService {

  private String applicationPath = System.getProperty("user.dir");
  private String repositoryPath = "/src/main/resources/repositories/";
  private String codeLanguage;
  private String analysisSonarqubeHook = "http://192.168.1.32:8080/sonarqube/hook";
  private String username = "admin";
  private String password = "admin";
  private String sonarURL = "http://localhost:" + 7000;
  private final RestTemplate restTemplate;

  public SonarQubeService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.basicAuthentication(username, password).build();
  }

  public void scanRepository(String projectName, String language) {
    this.codeLanguage = language;
    try {
      String repositoryFolder = applicationPath + repositoryPath;
      String projectPath = repositoryFolder + projectName;
      createSonarPropertiesFile(repositoryFolder, projectName);
      scanRespositoryInSonarqube(projectPath);
      removeRepositoryFromPath(projectPath);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      // e.printStackTrace();
    }
  }

  public boolean createSonarQubeProject(String projectName) {
    String createSonarProjectEndpoint = sonarURL + "api/projects/create";
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(createSonarProjectEndpoint)
        .queryParam("name", projectName).queryParam("project", projectName);
    ResponseEntity<Object> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null,
        Object.class);

    return (responseEntity.getStatusCode() == HttpStatus.OK) ? true : false;
  }

  public boolean updateWebhookPropertieSonarQubeProject(String projectName) {
    String createSonarWebhookEndpoint = sonarURL + "/api/webhooks/create";
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(createSonarWebhookEndpoint)
        .queryParam("name", "Sonarqube-Spring").queryParam("project", projectName)
        .queryParam("url", analysisSonarqubeHook);
    ResponseEntity<Object> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null,
        Object.class);

    return (responseEntity.getStatusCode() == HttpStatus.OK) ? true : false;
  }

  private void scanRespositoryInSonarqube(String path) throws InterruptedException, IOException {
    ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.redirectErrorStream(true);
    processBuilder.directory(new File(path));
    processBuilder.command("sonar-scanner", "-Dproject.settings=./sonar-project.properties");
    executeProcesses(processBuilder);
  }

  private void removeRepositoryFromPath(String path) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.redirectErrorStream(true);
    processBuilder.directory(new File(path));
  }

  private void createSonarPropertiesFile(String path, String projectName) throws IOException {
    File sonarPropertiesFile = new File(path + "/" + projectName + "/" + "sonar-project.properties");
    BufferedWriter writer = new BufferedWriter(new FileWriter(sonarPropertiesFile, true));
    writeSonarProperties(writer, projectName);
  }

  private void writeSonarProperties(BufferedWriter writer, String projectName) throws IOException {
    System.err.println(this.codeLanguage);
    if (this.codeLanguage.equals("java")) {
      writer.write("sonar.sources=src");
      writer.newLine();
      writer.write("sonar.java.binaries=.");
      writer.newLine();
    }
    writer.write("sonar.projectKey=" + projectName);
    writer.newLine();
    writer.close();
  }

  private boolean executeProcesses(ProcessBuilder processBuilder) throws IOException, InterruptedException {
    Process process = processBuilder.start();
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    readProcessResponse(reader);
    process.waitFor();
    process.destroy();
    return true;
  }

  private void readProcessResponse(BufferedReader reader) throws IOException {
    String line;
    StringBuilder output = new StringBuilder();
    while ((line = reader.readLine()) != null) {
      output.append(line + "\n");
      System.err.println(line + "\n");
    }
  }
}
