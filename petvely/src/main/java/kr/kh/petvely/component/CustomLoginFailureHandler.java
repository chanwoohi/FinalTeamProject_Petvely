package kr.kh.petvely.component;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler  {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    	System.out.println("CustomLoginFailureHandler");
    	
    	if(exception instanceof BadCredentialsException) {
        	System.out.println("BadCredentialsException");            
        }
    	else if(exception instanceof AuthenticationServiceException) {
        	System.out.println("AuthenticationServiceException");
		}
    	else {
        	System.out.println(exception);
    	}
    	
        request.setAttribute("error", exception);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/member/failed");
        requestDispatcher.forward(request, response);
    }
}