/**
 * 
 */
package com.hilti.recommendation;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Quick hack to enable CORS.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CorsFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		// init CorsFilter
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		LOGGER.info("Adding Access Control Response Headers");
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS, DELETE, PUT");
		response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, "
				+ "Access-Control-Request-Method, Access-Control-Request-Headers");
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

		// destroy corsFilter
	}

}
