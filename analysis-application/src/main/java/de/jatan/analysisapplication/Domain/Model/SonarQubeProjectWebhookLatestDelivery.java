package de.jatan.analysisapplication.Domain.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("unused")
public class SonarQubeProjectWebhookLatestDelivery {
  private String at;

  private String success;

  private String httpStatus;

  private String id;

  private String durationMs;

}
