package com.iotapi.restapp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.iotapi.restapp.util.CreateJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * this is the filter to handle session in the application it will keep a list
 * of JWTs all the currently logged in users in the application
 * 
 * @author ishan.juneja
 * @WebFilter annotation is used to declare a filter in a web application. The
 *            annotated class must extend the javax.servlet.Filter interface.
 * @Order is used to define sort order for components annotated with this
 *        annotation
 */
@Component
@Order(2)
public class SessionManager extends OncePerRequestFilter {

	/**
	 * list of the JWTs of the users currently logged in
	 */
	List<String> users = new ArrayList<String>();

	public void destroy() {

	}

	/**
	 * this method will filter the request and check if the request is either for
	 * login or registration or checking dbname which should not be checked for the
	 * authentication header
	 * 
	 * Date - 11/04/2018
	 * 
	 * @author ishan.juneja
	 */

	@Override
	protected void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain chain)
			throws ServletException, IOException {

		/**
		 * this is the logger instance for the current class
		 */

		// log the ip of the incoming request
		logger.info("incoming request from : " + httpRequest.getRemoteAddr());

		// take the authorization header from request
		final String authHeader = httpRequest.getHeader("Authorization");

		// if the request is for the method which checks dbnames which can
		// be of the following pattern then it should need to be checked for
		// authorization header
		// Pattern p = Pattern.compile("/customers/getdb/[A-z]*[a-z]*");
		String requestedpath = httpRequest.getServletPath();
//		Matcher matcher = p.matcher(requestedpath);
//		boolean found = matcher.find();

		// no need to check Authorization Header for login or register request
		if (/*found || */ requestedpath.equals("/customer/authenticate") || requestedpath.equals("/customer/register")
				|| httpRequest.getMethod().equalsIgnoreCase("options") || requestedpath.contains("inputs")) {

			// RequestReaders cannot be applied multiple times on a single
			// request
			// therefore it needs to be wrapped so that the httpRequest object
			// is
			// available in the further chain. Same goes for the response
			ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpRequest);
			ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpResponse);
			try {

				chain.doFilter(requestWrapper, responseWrapper);

			} finally {

				// get the request body that contains the username and create a
				// JSON Web Token for it
				// here the signing key should be read from the property file
				// and JWT is encrypted using it
				String headerSub = new String(requestWrapper.getContentAsByteArray());
				String headerContent = new CreateJWT().createJWT("id_" + Math.random(), "tomcat", headerSub, 10000L);
				responseWrapper.addHeader("Authorization", headerContent);
				responseWrapper.copyBodyToResponse();
				// add the jwt to the token list on server so that a valid token
				// for the application can be verified

				users.add(headerContent);

			}
			return;
		}

		// if the auth header is not valid and if it is null than the
		// AuthorizationHeaderException should be thrown
		if (authHeader == null || !authHeader.startsWith("Bearer ") || !users.contains(authHeader.substring(7))) {

			throw new ServletException("Missing or invalid Authorization header.");

		}

		// the authorization token
		final String token = authHeader.substring(7);

		// pass the value of user to the requested controller
		final Claims claims = Jwts.parser().setSigningKey("ishanjuneja").parseClaimsJws(token).getBody();
		httpRequest.setAttribute("claims", claims);
		chain.doFilter(httpRequest, httpResponse);

	}

}
