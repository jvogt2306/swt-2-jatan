package de.jatan.analysisapplication.services;

import java.io.*;

import org.springframework.stereotype.Service;

@Service
public class SonarQubeService {

  private String applicationPath = System.getProperty("user.dir");
  private String repositoryPath = "/src/main/resources/repositories/";
  private String codeLanguage;

  public void scanRepository(String projectName, String language) {
    this.codeLanguage = language;
    try {
      String repositoryFolder = applicationPath + repositoryPath;
      String projectPath = repositoryFolder + projectName;
      createSonarPropertiesFile(repositoryFolder, projectName);
      doScanRespository(projectPath);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      // e.printStackTrace();
    }
  }

  private void doScanRespository(String path) throws InterruptedException, IOException {

    ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.redirectErrorStream(true);
    processBuilder.directory(new File(path));
    processBuilder.command("sonar-scanner", "-Dproject.settings=./sonar-project.properties");
    executeProcesses(processBuilder);
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

    StringBuilder output = new StringBuilder();
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String line;
    while ((line = reader.readLine()) != null) {
      output.append(line + "\n");
      System.err.println(line + "\n");
    }
    process.waitFor();
    process.destroy();
    return true;
  }
}
