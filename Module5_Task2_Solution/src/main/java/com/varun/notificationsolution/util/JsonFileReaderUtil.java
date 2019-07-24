package com.varun.notificationsolution.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.varun.notificationsolution.configuration.ApplicationConfiguration;
import com.varun.notificationsolution.domain.Airline;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.List;

@Component
public class JsonFileReaderUtil {

  private final String filePath;
  private static final Type AIRLINE_TYPE = new TypeToken<List<Airline>>() {
  }.getType();
  public JsonFileReaderUtil(ApplicationConfiguration applicationConfiguration) {
    this.filePath = applicationConfiguration.getAirlineFilePath();
  }
  public List<Airline> getAllAirlines() throws Exception{
    File file = ResourceUtils.getFile("classpath:"+filePath);
    String content = new String(Files.readAllBytes(file.toPath()));
    Gson gson = new Gson();
    List<Airline> airlines = gson.fromJson(content, AIRLINE_TYPE); // contains the whole reviews list
    return airlines;
  }
}
