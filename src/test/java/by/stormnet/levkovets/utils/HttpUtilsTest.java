package by.stormnet.levkovets.utils;


import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.core.Is.is;


public class HttpUtilsTest {


    HttpServletRequest req = Mockito.spy(HttpServletRequest.class);

    {
        Mockito.doReturn("test").when(req).getParameter("test");
        Mockito.doReturn(null).when(req).getParameter("exist");
        Mockito.doReturn("1").when(req).getParameter("one");
        Mockito.doReturn("2").when(req).getParameter("two");

    }

    @Test
    public void isParameterExists() {
        Assert.assertTrue(HttpUtils.isParameterExists(req, "test"));
        Assert.assertFalse(HttpUtils.isParameterExists(req, "exist"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isParameterException() {
        HttpUtils.isParameterExists(req, "");
        HttpUtils.isParameterExists(req, " ");
    }

    @Test
    public void getIntParam() {
        Assert.assertThat(HttpUtils.getIntParam(req, "one"), is( 1));
        Assert.assertThat(HttpUtils.getIntParam(req, "two"), is( 2));
        Assert.assertNull(HttpUtils.getIntParam(req, "test"));
    }
}
