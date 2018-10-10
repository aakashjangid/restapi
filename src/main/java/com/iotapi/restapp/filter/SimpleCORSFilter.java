package com.iotapi.restapp.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * this is the filter to handle CORS requests to the application this filter
 * will configure the headers in response to the angular client used in our
 * application
 * 
 * @author ishan.juneja
 * @WebFilter annotation is used to declare a filter in a web application. The
 *            annotated class must extend the javax.servlet.Filter interface.
 * @Order is used to define sort order for components annotated with this
 *        annotation
 */
@Component 
@Order(1)
public class SimpleCORSFilter implements Filter {

	public SimpleCORSFilter() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		
		System.out.println("-------+++++++--------"+request.getRequestURL());
		System.out.println("-------+++++++--------"+request.getContentType());
		Enumeration<String> enums =  request.getAttributeNames();
		
		while(enums.hasMoreElements()) {
			System.out.println("#############################"+enums.nextElement());
		}
		
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", " Authorization, Content-Type, X-Requested-With");
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

}