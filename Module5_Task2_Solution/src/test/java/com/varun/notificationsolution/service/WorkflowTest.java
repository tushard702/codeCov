package com.varun.notificationsolution.service;

import com.varun.notificationsolution.domain.Airline;
import com.varun.notificationsolution.domain.Entry;
import com.varun.notificationsolution.util.CSVReaderUtil;
import com.varun.notificationsolution.util.EmailCreator;
import com.varun.notificationsolution.util.JsonFileReaderUtil;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;

public class WorkflowTest {

  private Workflow workflow;
  private CSVReaderUtil csvReaderUtil;
  private JsonFileReaderUtil jsonFileReaderUtil;
  private EmailCreator emailCreator;

  @BeforeMethod
  public void init() {
    csvReaderUtil = Mockito.mock(CSVReaderUtil.class);
    jsonFileReaderUtil = Mockito.mock(JsonFileReaderUtil.class);
    emailCreator = Mockito.mock(EmailCreator.class);
    workflow = new Workflow(csvReaderUtil, jsonFileReaderUtil, emailCreator);
  }

   @Test
  public void positiveTest() throws Exception{
    List<Airline> airlineList  = new ArrayList<>();
    Airline air = new Airline();
    air.setEmail("abc@gmail.com");
    air.setAirlineCode("XYZ");
    airlineList.add(air);

     Entry entry = new Entry();
     entry.setDateOfJourney(new Date());
     entry.setAirlineCode("XYZ");
     entry.setCustomerEmail("xvc");
     entry.setCustomerName("Mo");
     entry.setEntryType("Book");
     entry.setPnr("VVBCZA");
     entry.setCreateDate(new Date());
     entry.setRecordId(0);
     List<Entry> entryList = new ArrayList<>();
     entryList.add(entry);
    Mockito.when(csvReaderUtil.getAllEntries()).thenReturn(entryList);
     Mockito.when(jsonFileReaderUtil.getAllAirlines()).thenReturn(airlineList);

     workflow.execute();
    Mockito.verify(csvReaderUtil, Mockito.times(1)).getAllEntries();
    Mockito.verify(jsonFileReaderUtil, Mockito.times(1)).getAllAirlines();
    Mockito.verify(emailCreator, Mockito.times(1)).writeEmails(any(), any());
   }
  @Test
  public void emailCreatorException() throws Exception{

    Mockito.when(csvReaderUtil.getAllEntries()).thenThrow(new IOException());
    workflow.execute();
    Mockito.verify(csvReaderUtil, Mockito.times(1)).getAllEntries();
    Mockito.verify(jsonFileReaderUtil, Mockito.times(1)).getAllAirlines();
    Mockito.verify(emailCreator, Mockito.times(0)).writeEmails(any(), any());
  }

  @Test
  public void csvReaderException() throws Exception{
    Mockito.when(jsonFileReaderUtil.getAllAirlines()).thenThrow(new IOException());
    workflow.execute();
    Mockito.verify(csvReaderUtil, Mockito.times(0)).getAllEntries();
    Mockito.verify(jsonFileReaderUtil, Mockito.times(1)).getAllAirlines();
    Mockito.verify(emailCreator, Mockito.times(0)).writeEmails(any(), any());
  }
  @Test
  public void jsonReaderException() throws Exception{
    Mockito.doThrow(new IOException()).when(emailCreator).writeEmails(any(), any());
    workflow.execute();
    Mockito.verify(csvReaderUtil, Mockito.times(1)).getAllEntries();
    Mockito.verify(jsonFileReaderUtil, Mockito.times(1)).getAllAirlines();
    Mockito.verify(emailCreator, Mockito.times(1)).writeEmails(any(), any());
  }

}
