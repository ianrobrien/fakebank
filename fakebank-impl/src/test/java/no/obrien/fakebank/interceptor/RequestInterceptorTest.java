package no.obrien.fakebank.interceptor;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RequestInterceptorTest {

  /***
   * Test stub for FakebankInterceptor
   * The interceptor does not yet have logic so this test is essentially meaningless
   */
  @Test
  void validInterceptorResult() {
    var request = mock(HttpServletRequest.class);
    when(request.getRequestURI()).thenReturn("localhost:8080/accounts/111/balance");

    var requestInterceptor = new RequestInterceptor();
    assertTrue(requestInterceptor.preHandle(request, null, null));
  }
}
