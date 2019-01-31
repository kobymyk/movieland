package db2.onlineshop.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(final ServletContext context) throws ServletException {
        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.scan("db2.onlineshop");

        ContextLoaderListener listener = new ContextLoaderListener(root);
        context.addListener(listener);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(new GenericWebApplicationContext());
        ServletRegistration.Dynamic appServlet = context.addServlet("app", dispatcherServlet);
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
    }
}