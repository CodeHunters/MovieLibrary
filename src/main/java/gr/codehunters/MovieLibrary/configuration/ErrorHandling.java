package gr.codehunters.MovieLibrary.configuration;

import gr.codehunters.MovieLibrary.exceptions.AbstractLocalizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Configuration
public class ErrorHandling {
  @Bean
  HandlerExceptionResolverComposite getHandlerExceptionResolverComposite() {

    HandlerExceptionResolverComposite result = new HandlerExceptionResolverComposite();
    List<HandlerExceptionResolver> l = new ArrayList<HandlerExceptionResolver>();
    l.add(new ExceptionHandlerExceptionResolver());
    l.add(new ResponseStatusExceptionResolver());
    l.add(getSimpleMappingExceptionResolver());
    l.add(new DefaultHandlerExceptionResolver());
    result.setExceptionResolvers(l);
    return result;
  }

  SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
    SimpleMappingExceptionResolver result = new SimpleMappingExceptionResolver();
    Properties p = new Properties();
    p.put(AbstractLocalizedException.class.getName(), "errors/error");
    p.put(AccessDeniedException.class.getName(), "errors/403");
    result.setExceptionMappings(p);
    result.setDefaultErrorView("errors/defaultError");
    result.setDefaultStatusCode(HttpStatus.BAD_REQUEST.value());
    return result;
  }
}
