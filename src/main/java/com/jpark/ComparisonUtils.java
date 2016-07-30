package com.jpark;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by jpark on 7/30/16.
 */
class ComparisonUtils {
  static boolean possiblyContainsDuplicateKeys(final String propertiesText) {
    final Set<String> keys = new HashSet<>();
    for (String string : propertiesText.split("\r\n")) {
      final String key = StringUtils.trim(string.split("=")[0]);

      if (StringUtils.isBlank(key)) {
        continue;
      }

      if (keys.contains(key)) {
        return true;
      }

      keys.add(key);
    }
    return false;
  }

  static Set<String> getKeysNotInSecondProperties(final Properties first, final Properties second) {
    final Set<String> keysNotInSecondProperties = new HashSet<>();
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
    final Set<String> keysWithDifferentValues = new HashSet<>();

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
