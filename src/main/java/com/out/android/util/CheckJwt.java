package com.out.android.util;

import com.out.android.exception.CustomException;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

public final class CheckJwt {
	private  static final String authorization = "Authorization";

	private CheckJwt (){}

	public static String extract(HttpServletRequest request, String type){
		String header = request.getHeader(authorization);
		if(header != null){
			if(header.toLowerCase().startsWith(type.toLowerCase())){
				return header.substring(type.length()).trim();
			}else{
				throw new CustomException(HttpStatus.UNAUTHORIZED, "잘못된 토큰");
			}
		}
		return null;
	}
}
