package seo.dale.spring;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("log4jConfiguration", "/log4j.xml");
        Log4jConfigListener log4jListener = new Log4jConfigListener();
        servletContext.addListener(log4jListener);

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class);

        servletContext.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(WebMvcConfig.class);

        DispatcherServlet dispatcherServlet =  new DispatcherServlet(webApplicationContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true); // Throw NoHandlerFoundException instead of 404 HTTP Status

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
	    dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true"); // NoHandlerFoundException instead of 404 response
    }
    
}