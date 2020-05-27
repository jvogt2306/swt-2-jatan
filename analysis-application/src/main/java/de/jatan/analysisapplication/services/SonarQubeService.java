package de.jatan.analysisapplication.services;

import java.io.*;

import org.springframework.stereotype.Service;

@Service
public class SonarQubeService {

  public void scanRepository() {
    // String cmdSonarScanner = "sonar-scanner";
    String applicationPath = System.getProperty("user.dir");
    String repositoryPath = "/src/main/resources/Repositories/";
    String projectName = "projekt1-ar-react-test";
    String projectPath = applicationPath + repositoryPath + projectName;
    // String projectPath = applicationPath;
    System.err.println(projectPath);

    ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.command("bash", "cd", projectPath);
    processBuilder.command("sonar-scanner", "-Dsonar.sources=src -X");
    // processBuilder.command("pwd");
    // processBuilder.command("bash", cmdSonarScanner);
    try {
      Process process = processBuilder.start();
      StringBuilder output = new StringBuilder();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;

      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
      process.waitFor();
      System.err.println(output);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
