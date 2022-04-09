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
public class TomcatWebApp {
	public void start() {
        Tomcat  tomcat  = setupTomcat();
        Context context = setupContext(tomcat);

		addTimeServlet(tomcat, context);
		addRandomFactorialServlet(tomcat, context);

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

    private void addTimeServlet(final Tomcat  tomcat, final Context context) {
        HttpServlet timeServlet = new HttpServlet() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                resp.getWriter().println(LocalDateTime.now());
            }
        };

        tomcat.addServlet("/", "TimeServlet", timeServlet);
        context.addServletMappingDecoded("/server-time", "TimeServlet");
        context.addServletMappingDecoded("/", "TimeServlet");
    }

    private void addRandomFactorialServlet(final Tomcat  tomcat, final Context context) {
        HttpServlet randomFactorialServlet = new HttpServlet() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                Integer input = (int) (Math.random() * 100);
                Integer fact  = SimpleWebApp.factorial(input);
                System.out.println("Factorial of = " + input + " is " + fact);
                resp.getWriter().println(fact);
            }
        };

        tomcat.addServlet("/", "RandomFactorialServlet", randomFactorialServlet);
        context.addServletMappingDecoded("/random-factorial", "RandomFactorialServlet");
    }

    private static Integer factorial (Integer input) {
        System.out.println("Calculating factorial of " + input);
        return input == 1 ? 1 : input * factorial(input - 1);
    }
}