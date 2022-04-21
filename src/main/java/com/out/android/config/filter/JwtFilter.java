package com.out.android.config.filter;

import com.out.android.enums.Role;
import com.out.android.util.CheckJwt;
import com.out.android.util.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtFilter implements Filter {
	private final HandlerExceptionResolver handlerExceptionResolver;
	private final JwtProvider jwtProvider;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{
			CheckJwtAdepter jwtAdepter = CheckJwt::extract;

			String token = jwtAdepter.checkJwt((HttpServletRequest) request, "Bearer");

			if(!((HttpServletRequest) request).getMethod().equals("OPTIONS")){
				Set<GrantedAuthority> roles = new HashSet<>();

				if (null == token ) {
					System.out.println("null");
					request.setAttribute("idx", null);
					roles.add( new SimpleGrantedAuthority("ROLE_"+ Role.UNAUTH.toString()));
				}else{
					Claims claims = jwtProvider.decodingToken(token);
					request.setAttribute("idx", claims.get("idx"));
					roles.add( new SimpleGrantedAuthority("ROLE_"+Role.AUTH.toString()));
				}
				System.out.println("s");
				SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null, null, roles));
			}
			chain.doFilter(request,response);
		}catch (Exception e){
			e.printStackTrace();
			handlerExceptionResolver.resolveException((HttpServletRequest) request, (HttpServletResponse) response, null, e);
		}
	}
}

@FunctionalInterface
interface CheckJwtAdepter{
	public String checkJwt(HttpServletRequest request,String token);
}
