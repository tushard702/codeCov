package com.varun.notificationsolution.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Getter@Setter
public class ApplicationConfiguration {

  private String airlineFilePath;
  private String entryFilePath;
  private String emailFormat;


}

