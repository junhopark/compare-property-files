package com.jpark;

import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

/**
 * Created by jpark on 7/30/16.
 */
public class ComparisonUtilsTest {
  @Test
  public void test_possiblyContainsDuplicateKeys() {
    Assert.assertTrue(ComparisonUtils.possiblyContainsDuplicateKeys("key\r\nkey"));
    Assert.assertTrue(ComparisonUtils.possiblyContainsDuplicateKeys("key=\r\nkey"));
    Assert.assertTrue(ComparisonUtils.possiblyContainsDuplicateKeys("key\r\nkey="));

    Assert.assertTrue(ComparisonUtils.possiblyContainsDuplicateKeys("key = A\r\nkey=A"));

    Assert.assertFalse(ComparisonUtils.possiblyContainsDuplicateKeys(""));
    Assert.assertFalse(ComparisonUtils.possiblyContainsDuplicateKeys(" "));
    Assert.assertFalse(ComparisonUtils.possiblyContainsDuplicateKeys("\r\n"));

    Assert.assertFalse(ComparisonUtils.possiblyContainsDuplicateKeys("keyA\r\nkeyB"));
    Assert.assertFalse(ComparisonUtils.possiblyContainsDuplicateKeys("keyA=X\r\nkeyB=X"));
  }

  @Test
  public void test_getKeysWithDifferentValues() {
    Properties first = new Properties();
    first.setProperty("a", "");
    Properties second = new Properties();
    second.setProperty("a", "");
    Assert.assertTrue(ComparisonUtils.getKeysWithDifferentValues(first, second).isEmpty());

    first = new Properties();
    first.setProperty("a", "bob");
    second = new Properties();
    second.setProperty("a", "bob");
    Assert.assertTrue(ComparisonUtils.getKeysWithDifferentValues(first, second).isEmpty());

    first = new Properties();
    first.setProperty("a", "");
    second = new Properties();
    second.setProperty("a", "");
    Assert.assertTrue(ComparisonUtils.getKeysWithDifferentValues(first, second).isEmpty());

    first = new Properties();
    first.setProperty("a", "");
    second = new Properties();
    Assert.assertTrue(ComparisonUtils.getKeysWithDifferentValues(first, second).isEmpty());

    first = new Properties();
    second = new Properties();
    second.setProperty("a", "");
    Assert.assertTrue(ComparisonUtils.getKeysWithDifferentValues(first, second).isEmpty());

    first = new Properties();
    first.setProperty("a", "hello");
    second = new Properties();
    second.setProperty("a", "goodbye");
    Assert.assertEquals(1, ComparisonUtils.getKeysWithDifferentValues(first, second).size());

    first = new Properties();
    first.setProperty("a", "                      ");
    second = new Properties();
    second.setProperty("a", "    ");
    Assert.assertEquals(1, ComparisonUtils.getKeysWithDifferentValues(first, second).size());

    first = new Properties();
    first.setProperty("a", "X");
    first.setProperty("b", "X");
    second = new Properties();
    second.setProperty("a", "Y");
    Assert.assertEquals(1, ComparisonUtils.getKeysWithDifferentValues(first, second).size());
  }

  @Test
  public void test_areBothPropertiesEmpty() {
    Assert.assertTrue(ComparisonUtils.areBothPropertiesEmpty(null, null));

    Properties first = new Properties();
    Assert.assertTrue(ComparisonUtils.areBothPropertiesEmpty(first, null));
    Assert.assertTrue(ComparisonUtils.areBothPropertiesEmpty(null, first));

    Properties second = new Properties();
    Assert.assertTrue(ComparisonUtils.areBothPropertiesEmpty(first, second));

    first.setProperty("key", "value");
    Assert.assertFalse(ComparisonUtils.areBothPropertiesEmpty(first, second));
  }
}
