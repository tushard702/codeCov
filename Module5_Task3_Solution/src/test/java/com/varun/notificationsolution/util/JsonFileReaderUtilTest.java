package com.varun.notificationsolution.util;

import com.varun.notificationsolution.configuration.ApplicationConfiguration;
import com.varun.notificationsolution.domain.Airline;
import com.varun.notificationsolution.domain.Entry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class JsonFileReaderUtilTest {

  //Test exception
  @Test(expectedExceptions = IOException.class)
  public void filePathUnreadableTest() throws Exception{
    ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
    applicationConfiguration.setAirlineFilePath("random");
    JsonFileReaderUtil jsonFileReaderUtil = new JsonFileReaderUtil(applicationConfiguration);
    List<Airline> listOfAirlines = jsonFileReaderUtil.getAllAirlines();
  }


  //Test JSON conversion

  @Test
  public void positiveTest() throws Exception{
    ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
    applicationConfiguration.setAirlineFilePath("Airlines.json");
    JsonFileReaderUtil jsonFileReaderUtil = new JsonFileReaderUtil(applicationConfiguration);
    List<Airline> listOfAirlines = jsonFileReaderUtil.getAllAirlines();
    Assert.assertEquals(listOfAirlines.get(0).getAirlineCode(), "DAL");
    Assert.assertEquals(listOfAirlines.get(0).getEmail(), "obuuqkgzlzu@delta.com");
    Assert.assertEquals(listOfAirlines.get(1).getAirlineCode(), "COA");
    Assert.assertEquals(listOfAirlines.get(1).getEmail(), "obuuqkgzlzu@continental.com");
  }
}
