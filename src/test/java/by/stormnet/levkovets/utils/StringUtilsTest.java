package by.stormnet.levkovets.utils;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class StringUtilsTest {


    @Test
    public void isEmpty() {
        Assert.assertTrue(StringUtils.isEmpty(""));
        Assert.assertTrue(StringUtils.isEmpty(null));
        Assert.assertFalse(StringUtils.isEmpty("string"));
        Assert.assertFalse(StringUtils.isEmpty(" "));
    }

    @Test
    public void isNotEmpty() {
        Assert.assertFalse(StringUtils.isNotEmpty(""));
        Assert.assertFalse(StringUtils.isNotEmpty(null));
        Assert.assertTrue(StringUtils.isNotEmpty("string"));
        Assert.assertTrue(StringUtils.isNotEmpty(" "));
    }

    @Test
    public void isBlank() {
        Assert.assertTrue(StringUtils.isBlank(" "));
        Assert.assertTrue(StringUtils.isBlank(""));
        Assert.assertTrue(StringUtils.isBlank(null));
        Assert.assertFalse(StringUtils.isBlank("string"));
        Assert.assertFalse(StringUtils.isBlank(" str "));
    }

    @Test
    public void isNotBlank() {
        Assert.assertFalse(StringUtils.isNotBlank(" "));
        Assert.assertFalse(StringUtils.isNotBlank(""));
        Assert.assertFalse(StringUtils.isNotBlank(null));
        Assert.assertTrue(StringUtils.isNotBlank("string"));
        Assert.assertTrue(StringUtils.isNotBlank(" str "));
    }

    @Test
    public void simplePhoneNumber() {
        Assert.assertEquals(StringUtils.simplePhoneNumber("+375291111111"), "291111111");
        Assert.assertEquals(StringUtils.simplePhoneNumber("+375 (29) 111 11 11"), "291111111");
        Assert.assertEquals(StringUtils.simplePhoneNumber("+375(29)111-11-11"), "291111111");
        Assert.assertEquals(StringUtils.simplePhoneNumber("80291111111"), "291111111");
        Assert.assertEquals(StringUtils.simplePhoneNumber("8(029)1111111"), "291111111");
        Assert.assertEquals(StringUtils.simplePhoneNumber("8(029)111 11 11"), "291111111");
        Assert.assertEquals(StringUtils.simplePhoneNumber("8(029)111-11-11"), "291111111");
        Assert.assertEquals(StringUtils.simplePhoneNumber("8 029 111 11-11"), "291111111");
        Assert.assertEquals(StringUtils.simplePhoneNumber("111 11-11"), "1111111");
        Assert.assertNotEquals(StringUtils.simplePhoneNumber("8 029 111 11-11"), "29 222 22 22");
        Assert.assertNotEquals(StringUtils.simplePhoneNumber("8 029 111 11-11"), "292222222");

    }

}