package com.kishorgollapalliwar;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * This class is responsible to manage life cycle of tomcat server
 **/
public class TomcatWebApp {
	    public void start() {
        Tomcat  tomcat  = setupTomcat();
        Context context = setupContext(tomcat);

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        tomcat.getServer().await();
    }

    private Tomcat setupTomcat() {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(8080);

        return tomcat;
    }

    private Context setupContext(Tomcat tomcat) {
        String contextPath = "/";
        String docBase     = new File(".").getAbsolutePath();
        return tomcat.addContext(contextPath, docBase);
    }
}