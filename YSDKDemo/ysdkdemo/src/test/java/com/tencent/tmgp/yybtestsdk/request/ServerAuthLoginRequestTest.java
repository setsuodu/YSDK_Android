package com.tencent.tmgp.yybtestsdk.request;

import android.net.Uri;

import com.tencent.tmgp.yybtestsdk.DemoRobolectricTestRunner;
import com.tencent.tmgp.yybtestsdk.module.submodule.inner.ServerAuthLoginRequest;
import com.tencent.ysdk.framework.common.ePlatform;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.lang.reflect.Method;

@RunWith(DemoRobolectricTestRunner.class)
@Config(sdk = 28)
public class ServerAuthLoginRequestTest {

    @Before
    public void setup() {

    }

    @Test
    public void testQQReqUrl() throws Exception{
        ServerAuthLoginRequest httpRequest = new ServerAuthLoginRequest("openid2222","token3333", ePlatform.QQ,null);
        Assert.assertNotNull(httpRequest);

        Method method = ServerAuthLoginRequest.class.getDeclaredMethod("getUrl");
        method.setAccessible(true);
        String url = (String) method.invoke(httpRequest);

        Assert.assertNotNull(url);

        Uri uri = Uri.parse(url);
        Assert.assertNotNull(uri);


    }

}
