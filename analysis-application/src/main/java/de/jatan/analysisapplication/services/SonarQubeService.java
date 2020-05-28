package de.jatan.analysisapplication.services;

import java.io.*;

import org.springframework.stereotype.Service;

@Service
public class SonarQubeService {

  private String applicationPath = System.getProperty("user.dir");
  private String repositoryPath = "/src/main/resources/repositories/";

  public void scanRepository() {
    try {
      String projectName = "projekt1-ar-react-test";
      String repositoryFolder = applicationPath + repositoryPath;
      String projectPath = repositoryFolder + projectName;
      createSonarPropertiesFile(repositoryFolder, projectName);
      doScanRespository(projectPath);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void doScanRespository(String path) throws InterruptedException, IOException {
    ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.command("bash", "cd", path);
    processBuilder.command("sonar-scanner");
    executeProcesses(processBuilder);
  }

  private void createSonarPropertiesFile(String path, String projectName) throws IOException {
    File sonarPropertiesFile = new File("sonar-project.properties");
    BufferedWriter writer = new BufferedWriter(new FileWriter(sonarPropertiesFile, true));
    writeSonarProperties(writer, projectName);
  }

  private void writeSonarProperties(BufferedWriter writer, String projectName) throws IOException {
    writer.write("sonar.sources=src");
    writer.newLine();
    // writer.write("sonar.java.binaries=bin");
    // writer.newLine();
    writer.write("sonar.projectKey=" + projectName);
    writer.close();
  }

  private void executeProcesses(ProcessBuilder processBuilder) throws InterruptedException, IOException {
    Process process = processBuilder.start();
    StringBuilder output = new StringBuilder();
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String line;
    while ((line = reader.readLine()) != null) {
      output.append(line + "\n");
    }
    process.waitFor();
    System.err.println(output);
  }
}
