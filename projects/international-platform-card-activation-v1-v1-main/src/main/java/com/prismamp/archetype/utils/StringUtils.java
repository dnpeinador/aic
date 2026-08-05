package com.prismamp.archetype.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtils {

  public static String getQuery(String path) {
    Map<String, String> queryParams =
        Arrays.stream(path.split("\\?"))
            .skip(1)
            .flatMap(queryString -> Arrays.stream(queryString.split("&")))
            .map(param -> param.split("="))
            .collect(Collectors.toMap(p -> p[0], p -> p[1]));

    return queryParams.toString();
  }

  public static String getIp(String remoteAddress) {
    String ipAddress = "-";
    Pattern pattern = Pattern.compile("\\[([0-9a-fA-F:]+)\\]:(\\d+)");
    Matcher matcher = pattern.matcher(remoteAddress);
    if (matcher.find()) {
      ipAddress = matcher.group(1);
    }
    return ipAddress;
  }
}
