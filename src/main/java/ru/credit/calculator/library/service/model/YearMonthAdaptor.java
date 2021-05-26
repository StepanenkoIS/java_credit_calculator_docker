package ru.credit.calculator.library.service.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import java.time.YearMonth;
import java.time.format.DateTimeParseException;

public class YearMonthAdaptor extends XmlAdapter<String, YearMonth> {

  @Override
  public YearMonth unmarshal(String str) throws Exception {
    try {
      return YearMonth.parse(str);
    } catch (DateTimeParseException exception) {
      return null;
    }
  }

  @Override
  public String marshal(YearMonth date) throws Exception {
    return date.toString();
  }
}
