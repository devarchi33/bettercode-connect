package com.bettercode.connect.utils;

public class NumberUtil {

  public static String addThousandUnit(String data) {
    return new java.text.DecimalFormat("#,###").format(Integer.parseInt(data));
  }

  public static String removeThousandUnit(String data) {
    return data.replaceAll("\\,","");
  }

}
