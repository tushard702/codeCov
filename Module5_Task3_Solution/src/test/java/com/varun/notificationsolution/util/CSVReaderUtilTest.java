package com.varun.notificationsolution.util;

import com.varun.notificationsolution.configuration.ApplicationConfiguration;
import com.varun.notificationsolution.domain.Entry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class CSVReaderUtilTest {



  //Test exception
  @Test(expectedExceptions = IOException.class)
  public void filePathUnreadableTest() throws Exception{
    ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
    applicationConfiguration.setEntryFilePath("random");
    CSVReaderUtil csvReaderUtil = new CSVReaderUtil(applicationConfiguration);
    List<Entry> listOfEntries = csvReaderUtil.getAllEntries();
  }


  //Test CSV conversion

  @Test
  public void positiveTest() throws Exception{
    ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
    applicationConfiguration.setEntryFilePath("Entries.csv");
    CSVReaderUtil csvReaderUtil = new CSVReaderUtil(applicationConfiguration);
    List<Entry> listOfEntries = csvReaderUtil.getAllEntries();
    Assert.assertEquals(listOfEntries.get(0).getCustomerName(), "Faith Dorrough");
    Assert.assertEquals(listOfEntries.get(1).getCustomerName(), "Ethan Goods");
    Assert.assertEquals(listOfEntries.get(2).getCustomerName(), "Erma Terrill");
    Assert.assertEquals(listOfEntries.get(0).getEntryType(), "Book");
    Assert.assertEquals(listOfEntries.get(1).getEntryType(), "Cancel");
    Assert.assertEquals(listOfEntries.get(2).getEntryType(), "Modify");


  }

}
