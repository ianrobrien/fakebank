package dev.ianrobrien.fakebank.config;

import dev.ianrobrien.fakebank.interceptor.RequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * Allows setting configuration on the REST client used by the application
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {

  /***
   * Add a custom request interceptor
   * @param registry the interceptor registry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new RequestInterceptor());
  }
}
