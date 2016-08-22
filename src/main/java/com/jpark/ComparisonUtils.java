package com.jpark;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by jpark on 7/30/16.
 */
class ComparisonUtils {
  static Set<String> getDuplicatedKeys(final String propertiesText) {
    final SortedSet<String> duplicatedKeys = new TreeSet<>();
    final Set<String> keys = new HashSet<>();
    for (String string : propertiesText.split("\r\n")) {
      final String key = StringUtils.trim(string.split("=")[0]);

      if (StringUtils.isBlank(key) || StringUtils.startsWith(key, "#")) {
        continue;
      }

      if (keys.contains(key)) {
        duplicatedKeys.add(key);
      }

      keys.add(key);
    }

    return duplicatedKeys;
  }

  static Set<String> getKeysNotInSecondProperties(final Properties first, final Properties second) {
    final SortedSet<String> keysNotInSecondProperties = new TreeSet<>();
    final Set secondPropertiesKeys = second.keySet();
    for (Object key : first.keySet()) {
      final String keyString = (String) key;

      if (!secondPropertiesKeys.contains(keyString)) {
        keysNotInSecondProperties.add(keyString);
      }
    }
    return keysNotInSecondProperties;
  }

  static Set<String> getKeysWithDifferentValues(final Properties first, final Properties second) {
    final Set<Object> keysInBothProperties = new HashSet<>();
    final SortedSet<String> keysWithDifferentValues = new TreeSet<>();

    keysInBothProperties.addAll(first.keySet());
    keysInBothProperties.addAll(second.keySet());
    keysInBothProperties.retainAll(second.keySet());
    keysInBothProperties.retainAll(first.keySet());

    for (Object key : keysInBothProperties) {
      final String keyString = (String) key;

      if (!StringUtils.equals(first.getProperty(keyString), second.getProperty(keyString))) {
        keysWithDifferentValues.add(keyString);
      }
    }

    return keysWithDifferentValues;
  }

  static boolean areBothPropertiesEmpty(final Properties first, final Properties second) {
    return (first == null || first.isEmpty()) && (second == null || second.isEmpty());
  }
}
