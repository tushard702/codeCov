package com.varun.notificationsolution.util;

import com.varun.notificationsolution.domain.Airline;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UtilityTest {

  @Test
  public void airlineListToMapNullTest() {
    HashMap<String, String> airlineMap =  Utility.airlineListToMap(null);
    Assert.assertTrue(airlineMap.isEmpty());
  }
  @Test
  public void airlineListToMapTest() {
    List<Airline> airlineList = new ArrayList<>();
    Airline one = new Airline();
    one.setAirlineCode("ABC");
    one.setEmail("a@gmail.com");
    Airline two = new Airline();
    two.setAirlineCode("XYZ");
    two.setEmail("b@gmail.com");
    Airline three = new Airline();
    three.setAirlineCode("AAA");
    three.setEmail("c@gmail.com");
    airlineList.add(one);
    airlineList.add(two);
    airlineList.add(three);

    HashMap<String, String> airlineMap =  Utility.airlineListToMap(airlineList);
    Assert.assertFalse(airlineMap.isEmpty());
    Assert.assertEquals(airlineMap.size(), 3);
    Assert.assertEquals(airlineMap.get("ABC"), "a@gmail.com");
    Assert.assertEquals(airlineMap.get("XYZ"), "b@gmail.com");
    Assert.assertEquals(airlineMap.get("AAA"), "c@gmail.com");

  }

}
