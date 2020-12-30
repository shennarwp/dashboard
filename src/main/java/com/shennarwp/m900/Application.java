package com.shennarwp.m900;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.vaadin.artur.helpers.LaunchUtil;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = Lumo.class, variant = Lumo.DARK)
@PWA(name = "dashboard", shortName = "dashboard")
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    /* need to disable devtools.restart because LinkEntity is loaded by RestartClassLoader
     * instead of base ClassLoader, this will cause issue
     * where casting to LinkEntity object to fail since it is loaded by different ClassLoader
     * Reference: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-devtools-restart */
    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
    }

}
