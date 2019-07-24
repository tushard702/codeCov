package com.varun.notificationsolution.util;

import com.varun.notificationsolution.domain.Entry;

import java.util.Comparator;

public class EntryComparator implements Comparator<Entry> {
  public int compare(Entry e1, Entry e2) {
    if(e1.getDateOfJourney().compareTo(e2.getDateOfJourney()) > 0) {
      return 1;
    } else if(e1.getDateOfJourney().compareTo(e2.getDateOfJourney()) < 0) {
      return -1;
    } else return 0;
  }
}
