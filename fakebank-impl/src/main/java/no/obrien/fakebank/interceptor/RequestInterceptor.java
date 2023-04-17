package no.obrien.fakebank.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/***
 * Http request interceptor that allows executing functions before and
 * after the request is handled
 */
@Component
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

  /***
   * Executed before the request is handled
   * @param request the http request
   * @param response the http response
   * @return true if the execution should proceed
   */
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    // perform logic such as parsing header values, inserting a trace id or
    // performing validation
    log.info("In interceptor for request: {}", request.getRequestURI());
    return true;
  }
}
