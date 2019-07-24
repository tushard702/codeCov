package com.varun.notificationsolution.service;


import com.varun.notificationsolution.configuration.ApplicationConfiguration;
import com.varun.notificationsolution.domain.Airline;
import com.varun.notificationsolution.domain.Entry;
import com.varun.notificationsolution.util.EntryComparator;
import com.varun.notificationsolution.util.CSVReaderUtil;
import com.varun.notificationsolution.util.EmailCreator;
import com.varun.notificationsolution.util.JsonFileReaderUtil;
import com.varun.notificationsolution.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class Workflow {


  final ApplicationConfiguration applicationConfiguration;
  @Autowired
  public Workflow(ApplicationConfiguration applicationConfiguration) {
    this.applicationConfiguration = applicationConfiguration;

  }
  public void execute() {
    List<Airline> airlineList = null;
    //Read the airline Information and store it in a
    try{
      JsonFileReaderUtil jsonFileReaderUtil = new JsonFileReaderUtil(applicationConfiguration);
      airlineList = jsonFileReaderUtil.getAllAirlines();
    }catch (Exception e) {
      System.out.println(e);
      return;
    }
    //Create a hashmap of airline with its email address
    HashMap<String, String> airlineMap = Utility.airlineListToMap(airlineList);

    //Get the list of entries
    List<Entry> entryList = null;

    try{
      CSVReaderUtil csvReaderUtil  = new CSVReaderUtil(applicationConfiguration);
      entryList = csvReaderUtil.getAllEntries();
    } catch (Exception e) {
      System.out.println(e);
      return;
    }

    //Initialize the priority queue
    HashMap<String, PriorityQueue<Entry>> entryPriorityMap = new HashMap<>();
    for (Airline airline: airlineList) {
      entryPriorityMap.put(airline.getAirlineCode(), new PriorityQueue<>(100, new EntryComparator()));
    }

    //Insert the entries into the PriorityQueue
    for (Entry entry: entryList) {
      entryPriorityMap.get(entry.getAirlineCode()).add(entry);
    }

    try {
      EmailCreator emailCreator = new EmailCreator(applicationConfiguration);
      emailCreator.writeEmails(entryPriorityMap, airlineMap);
    } catch (Exception e) {
      System.out.println(e);
      return;
    }

  }
}
