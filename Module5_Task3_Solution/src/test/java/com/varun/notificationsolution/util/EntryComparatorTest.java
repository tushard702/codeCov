package com.varun.notificationsolution.util;

import com.varun.notificationsolution.domain.Entry;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

public class EntryComparatorTest {

  private EntryComparator entryComparator;

  @BeforeClass
  public void init() {
    entryComparator = new EntryComparator();
  }

  @Test
  public void firstDateGreater() {
    Entry e1 = new Entry();
    Entry e2 = new Entry();
    e1.setDateOfJourney(new Date(2012, 12, 12));
    e2.setDateOfJourney(new Date(2013, 12, 12));

    Assert.assertEquals(-1, entryComparator.compare(e1, e2));
  }

  @Test
  public void secondDateGreater() {
    Entry e1 = new Entry();
    Entry e2 = new Entry();
    e1.setDateOfJourney(new Date(2013, 12, 12));
    e2.setDateOfJourney(new Date(2012, 12, 12));

    Assert.assertEquals(1, entryComparator.compare(e1, e2));
  }

  @Test
  public void equalDates() {
    Entry e1 = new Entry();
    Entry e2 = new Entry();
    e1.setDateOfJourney(new Date(2012, 12, 12));
    e2.setDateOfJourney(new Date(2012, 12, 12));

    Assert.assertEquals(0, entryComparator.compare(e1, e2));
  }
}
