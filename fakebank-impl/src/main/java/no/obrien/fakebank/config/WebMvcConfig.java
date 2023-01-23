package no.obrien.fakebank.config;

import no.obrien.fakebank.interceptor.RequestInterceptor;
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
   * @param registry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new RequestInterceptor());
  }
}
