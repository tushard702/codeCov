package com.varun.notificationsolution.util;

import com.varun.notificationsolution.domain.Airline;

import java.util.HashMap;
import java.util.List;

public class Utility {
  public static HashMap<String, String> airlineListToMap(List<Airline> airlineList) {
    HashMap<String,String> airlineMap = new HashMap<>();
    if(airlineList == null) {
      return airlineMap;
    }
    for (Airline airline: airlineList) {
      airlineMap.put(airline.getAirlineCode(), airline.getEmail());
      System.out.println(airline.getAirlineCode() + airline.getEmail());
    }
    return airlineMap;
  }
}
