package com.varun.notificationsolution.util;


import com.opencsv.CSVReader;
import com.varun.notificationsolution.configuration.ApplicationConfiguration;
import com.varun.notificationsolution.domain.Entry;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVReaderUtil {

  private final String filePath;

  public CSVReaderUtil(ApplicationConfiguration applicationConfiguration) {
    this.filePath = applicationConfiguration.getEntryFilePath();
  }

  public List<Entry> getAllEntries() throws IOException, ParseException {


    File file = ResourceUtils.getFile("classpath:"+filePath);
    FileReader filereader = new FileReader(file);
    CSVReader csvReader = new CSVReader(filereader);
    String[] nextRecord;
    List<Entry> entryList = new ArrayList<>();
    csvReader.readNext();
    while ((nextRecord = csvReader.readNext()) != null) {
      Entry entry = new Entry();
      entry.setRecordId(Integer.parseInt(nextRecord[0]));
      entry.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").parse(nextRecord[1]));
      entry.setPnr(nextRecord[2]);
      entry.setEntryType(nextRecord[3]);
      entry.setCustomerName(nextRecord[4]);
      entry.setCustomerEmail(nextRecord[5]);
      entry.setAirlineCode(nextRecord[6]);
      entry.setDateOfJourney(new SimpleDateFormat("yyyy-MM-dd").parse(nextRecord[7]));
      entryList.add(entry);
    }

    return entryList;
  }
}
