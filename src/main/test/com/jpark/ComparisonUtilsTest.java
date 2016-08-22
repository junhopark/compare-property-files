package com.jpark;

import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

/**
 * Created by jpark on 7/30/16.
 */
public class ComparisonUtilsTest {
  @Test
  public void test_getDuplicatedKeys() {
    Assert.assertTrue(ComparisonUtils.getDuplicatedKeys("key\r\nkey").toArray()[0].equals("key"));
    Assert.assertTrue(ComparisonUtils.getDuplicatedKeys("key=\r\nkey").toArray()[0].equals("key"));
    Assert.assertTrue(ComparisonUtils.getDuplicatedKeys("key\r\nkey=").toArray()[0].equals("key"));

    Assert.assertTrue(ComparisonUtils.getDuplicatedKeys("key = A\r\nkey=A").toArray()[0].equals("key"));

    Assert.assertTrue(ComparisonUtils.getDuplicatedKeys("").isEmpty());
    Assert.assertTrue(ComparisonUtils.getDuplicatedKeys(" ").isEmpty());
    Assert.assertTrue(ComparisonUtils.getDuplicatedKeys("\r\n").isEmpty());

    // Let's ignore lines that start with '#'.  It's a comment.
    Assert.assertTrue(ComparisonUtils.getDuplicatedKeys("#test\r\n#test").isEmpty());

    Assert.assertTrue(ComparisonUtils.getDuplicatedKeys("keyA\r\nkeyB").isEmpty());
    Assert.assertTrue(ComparisonUtils.getDuplicatedKeys("keyA=X\r\nkeyB=X").isEmpty());
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
