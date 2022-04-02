package com.kishorgollapalliwar;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * This class is responsible to manage life cycle of tomcat server
 **/
public class SimpleWebApp {
	public void start() {
		Tomcat tomcat = setupTomcat();
	}

    private Tomcat setupTomcat() {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(8080);

        return tomcat;
    }
}