package com.tencent.tmgp.yybtestsdk.appearance;

import com.tencent.tmgp.yybtestsdk.DemoRobolectricTestRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@RunWith(DemoRobolectricTestRunner.class)
@Config(sdk = 28)
public class UtilTest {

    @Test
    public void testMD5WithNull() throws Exception{
        String rest = Util.getMD5(null);
        Assert.assertEquals("",rest);
    }

    @Test
    public void testMD5WithStr() throws Exception{
        String rest = Util.getMD5("花泽香菜");
        Assert.assertTrue("32D1011EF994C6F153739C27DDD1A89B".equalsIgnoreCase(rest));
    }
}
