package seo.dale.spring;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("log4jConfiguration", "/log4j.xml");
        Log4jConfigListener log4jListener = new Log4jConfigListener();
        servletContext.addListener(log4jListener);

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class, CacheConfig.class, DatabaseConfig.class);

        servletContext.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(WebConfig.class);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(webApplicationContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
	    dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true"); // NoHandlerFoundException instead of 404 response
    }
    
}
