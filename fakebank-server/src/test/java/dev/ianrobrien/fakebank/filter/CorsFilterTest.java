package dev.ianrobrien.fakebank.filter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CorsFilterTest {

  @Test
  void init() {
    assertDoesNotThrow(() -> {
      var corsFilter = new CorsFilter();
      corsFilter.init(new MockFilterConfig());
    });
  }

  @Test
  void doFilter() {
    assertDoesNotThrow(() -> {
      var response = new MockHttpServletResponse();

      var corsFilter = new CorsFilter();
      corsFilter.doFilter(
          new MockHttpServletRequest(),
          response,
          new MockFilterChain());

      assertEquals("*", response.getHeader("Access-Control-Allow-Origin"));
      assertEquals("GET, POST, PUT, DELETE", response.getHeader("Access-Control-Allow-Methods"));
      assertEquals("*", response.getHeader("Access-Control-Allow-Headers"));
      assertEquals("true", response.getHeader("Access-Control-Allow-Credentials"));
      assertEquals("3600", response.getHeader("Access-Control-Max-Age"));
    });
  }

  @Test
  void destroy() {
    assertDoesNotThrow(() -> {
      var corsFilter = new CorsFilter();
      corsFilter.destroy();
    });
  }
}
