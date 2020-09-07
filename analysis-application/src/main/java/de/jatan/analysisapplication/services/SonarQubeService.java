package de.jatan.analysisapplication.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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
import de.jatan.analysisapplication.config.GlobalConfiguration;

@Service
public class SonarQubeService {
  private static final String APPLICATIONPATH = System.getProperty("user.dir");
  private static final String REPOSITORYPATH = "/src/main/resources/repositories/";
  public static final String REPOSITORYFOLDERABSOLUTE = APPLICATIONPATH + REPOSITORYPATH;
  private final RestTemplate restTemplate;

  public SonarQubeService() {
    final RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
    this.restTemplate = restTemplateBuilder
        .basicAuthentication(GlobalConfiguration.SonarUser, GlobalConfiguration.SonarPassword).build();
  }

  public boolean scanRepository(final String projectName, final String language) {
    try {
      final String projectPath = REPOSITORYFOLDERABSOLUTE + projectName;
      createSonarPropertiesFile(REPOSITORYFOLDERABSOLUTE, projectName, language);
      scanRespositoryInSonarqube(projectPath);
      removeRepositoryFromPath(projectPath);
    } catch (final FileNotFoundException e) {
      e.printStackTrace();
    } catch (final IOException e) {
      e.printStackTrace();
    } catch (final InterruptedException e) {
      e.printStackTrace();
    } catch (final NullPointerException e) {
      e.printStackTrace();
    }
    return true;
  }

  public boolean createSonarQubeProject(final String projectName) {
    if (checkIfProjectExist(projectName)) {
      return true;
    }

    final String createSonarProjectEndpoint = GlobalConfiguration.SonarAdress + "/api/projects/create";
    final String project = projectName;
    final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(createSonarProjectEndpoint)
        .queryParam("name", projectName).queryParam("project", project);
    final ResponseEntity<Object> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null,
        Object.class);
    return (responseEntity.getStatusCode() == HttpStatus.OK);
  }

  public boolean updateWebhookPropertieSonarQubeProject(final String projectName) {
    if (checkIfWebhookExist(projectName))
      return true;
    final String createSonarWebhookEndpoint = GlobalConfiguration.SonarAdress + "/api/webhooks/create";
    final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(createSonarWebhookEndpoint)
        .queryParam("name", "Sonarqube-Spring").queryParam("project", projectName)
        .queryParam("url", GlobalConfiguration.JatanSonarHook);
    final ResponseEntity<Object> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null,
        Object.class);
    return (responseEntity.getStatusCode() == HttpStatus.OK);
  }

  public boolean scanRespositoryInSonarqube(final String path) throws InterruptedException, IOException {
    final ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.redirectErrorStream(true);
    processBuilder.directory(new File(path));
    processBuilder.command(GlobalConfiguration.SonarScannerPath, "-Dproject.settings=./sonar-project.properties",
        "-Dsonar.host.url=" + GlobalConfiguration.SonarAdress, "-Dsonar.login=" + GlobalConfiguration.SonarLogin);
    processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
    executeProcesses(processBuilder);
    return true;
  }

  public boolean removeRepositoryFromSonarQube(final SonarQubeProject project) {
    final String deleteSonarProjectEndpoint = GlobalConfiguration.SonarAdress + "/api/projects/delete";
    final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(deleteSonarProjectEndpoint)
        .queryParam("project", project.getKey());
    final ResponseEntity<Object> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null,
        Object.class);
    return (responseEntity.getStatusCode() == HttpStatus.OK);
  }

  public boolean removeRepositoryFromPath(final String path) {
    return FileSystemUtils.deleteRecursively(new File(path));
  }

  public boolean createSonarPropertiesFile(final String path, final String projectName, final String language)
      throws IOException {
    final File sonarPropertiesFile = new File(path + "/" + projectName + "/" + "sonar-project.properties");
    final BufferedWriter writer = new BufferedWriter(new FileWriter(sonarPropertiesFile, true));
    writeSonarProperties(writer, projectName, language);
    return true;
  }

  public boolean checkIfProjectExist(final String projectName) {
    final String searchSonarProjectEndpoint = GlobalConfiguration.SonarAdress + "/api/projects/search";
    final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(searchSonarProjectEndpoint)
        .queryParam("projects", projectName);
    final ResponseEntity<SonarQubeProjectResponse> responseEntity = restTemplate.exchange(uriBuilder.toUriString(),
        HttpMethod.POST, null, SonarQubeProjectResponse.class);
    return (responseEntity.getBody().getComponents().length != 0);
  }

  public boolean checkIfWebhookExist(final String projectName) {
    final String searchSonarProjectEndpoint = GlobalConfiguration.SonarAdress + "/api/webhooks/list";
    final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(searchSonarProjectEndpoint)
        .queryParam("project", projectName);
    final ResponseEntity<SonarQubeProjectWebhook> responseEntity = restTemplate.exchange(uriBuilder.toUriString(),
        HttpMethod.POST, null, SonarQubeProjectWebhook.class);
    return (responseEntity.getBody().getWebhooks().length != 0);
  }

  public boolean writeSonarProperties(final BufferedWriter writer, final String projectName, final String language)
      throws IOException {
    if (language.equals("Java")) {
      writer.write("sonar.java.binaries=.");
      writer.newLine();
    }
    writer.write("sonar.projectKey=" + projectName);
    writer.newLine();
    writer.close();
    return true;
  }

  public boolean executeProcesses(final ProcessBuilder processBuilder) throws IOException, InterruptedException {
    final Process process = processBuilder.start();
    process.waitFor();
    process.destroy();
    return true;
  }
}
