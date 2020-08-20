package com.tencent.tmgp.yybtestsdk;


import org.junit.runners.model.InitializationError;
import org.robolectric.MavenRoboSettings;
import org.robolectric.RobolectricTestRunner;

public class DemoRobolectricTestRunner extends RobolectricTestRunner {
    static {
        MavenRoboSettings.setMavenRepositoryId("tencent_maven");
        MavenRoboSettings.setMavenRepositoryUrl("https://mirrors.tencent.com/nexus/repository/maven-public/");
    }


    public DemoRobolectricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }
}
