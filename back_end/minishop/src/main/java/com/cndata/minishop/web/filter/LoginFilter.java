package com.cndata.minishop.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;

import com.cndata.minishop.web.cache.Cache;
import com.cndata.minishop.web.cache.CacheManager;

@Order(2)
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		boolean requireAuth = req.getRequestURL().toString().indexOf("/auth/") > 0;
		if(requireAuth){
			String token = req.getHeader("Authorization");
			if(null!=token && !"".equals(token)){
				Cache cache = CacheManager.getCacheInfo(token);
				if(null!=cache){
					chain.doFilter(request, response);
				}else{
					res.sendRedirect("/#/login");
				}
			}else{
				res.sendRedirect("/#/login");
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}
}
