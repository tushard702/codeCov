package com.varun.notificationsolution.util;

import com.varun.notificationsolution.configuration.ApplicationConfiguration;
import com.varun.notificationsolution.domain.Entry;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

@Component
public class EmailCreator {

  private final String emailFormat;
  public EmailCreator(ApplicationConfiguration applicationConfiguration) {
    this.emailFormat = applicationConfiguration.getEmailFormat();

  }
  public void writeEmails(HashMap<String, PriorityQueue<Entry>> entryPriorityMap, HashMap<String, String> airlineMap) throws Exception{

    for (Map.Entry<String, PriorityQueue<Entry>> item: entryPriorityMap.entrySet()) {
      String airlineCode = item.getKey();
      String airlineEmail = airlineMap.get(airlineCode);
      int index = 0;
      for(Entry entry: item.getValue()) {
        writeToFile(airlineCode, String.format(emailFormat, airlineEmail,  entry.getEntryType(), entry.getPnr(), entry.getEntryType(), entry.getPnr(), entry.getCustomerName(), entry.getCustomerEmail(), entry.getDateOfJourney().toString()), index);
        index++;
      }
    }

  }


  private void writeToFile(String airlineCode, String email, int index) throws Exception {

    System.out.println(email);
    File file = new File("./Emails/"+airlineCode);
    file.mkdirs();

    FileWriter fw=new FileWriter(String.format("./Emails/%s/EmailNo%d", airlineCode,index));
    fw.write(email);
    fw.close();
  }

}
