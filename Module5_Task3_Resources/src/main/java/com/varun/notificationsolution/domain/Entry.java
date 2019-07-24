package com.varun.notificationsolution.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class Entry {
  int recordId;
  Date createDate;
  String pnr;
  String entryType;
  String customerName;
  String customerEmail;
  String airlineCode;
  Date dateOfJourney;

}
