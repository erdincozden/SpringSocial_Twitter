package spring.mvc.social.web;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.DispatcherServlet;


import spring.mvc.social.config.WebMvcContextConfiguration;

public class SocialWebApplicationInitializer implements WebApplicationInitializer {

	private static final String DISPATCHER_SERVLET_NAME="dispatcher";
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		registerDispatcherServlet(servletContext);
        registerHttPutFormContentFilter(servletContext);
        registerHiddenHttpMethodFilter(servletContext);
	}

	private void registerDispatcherServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext dispatcherContext=createContext(WebMvcContextConfiguration.class);
		ServletRegistration.Dynamic dispatcher;
		dispatcher=servletContext.addServlet(DISPATCHER_SERVLET_NAME,new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}
	
	private AnnotationConfigWebApplicationContext createContext(final Class<?>... annotatedClassses){
		AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
		context.register(annotatedClassses);
		return context;
	}
    private void registerHttPutFormContentFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic registration = servletContext.addFilter("httpPutFormContentFilter",
                HttpPutFormContentFilter.class);
        registration.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false,
                DISPATCHER_SERVLET_NAME);

    }

    private void registerHiddenHttpMethodFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic registration = servletContext.addFilter("hiddenHttpMethodFilter",
                HiddenHttpMethodFilter.class);
        registration.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false,
                DISPATCHER_SERVLET_NAME);
    }

}
