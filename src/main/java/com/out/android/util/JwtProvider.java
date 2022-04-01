package com.out.android.util;

import com.out.android.exception.CustomException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

@Component
public class JwtProvider {
	@Value("${jwt.auth.access}")
	String ACCESS_KEY;

//	public String encodingToken(Integer idx){
//		return Jwts.builder().
//	}

	private Key makeKey(){
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] secretKey;
		try{
			secretKey = DatatypeConverter.parseBase64Binary(ACCESS_KEY);
			return new SecretKeySpec(secretKey, signatureAlgorithm.getJcaName());
		}catch (Exception e){
			e.printStackTrace();
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "key 생성 오류");
		}
	}

	public Claims decodingToken(String token){
		try{
			return Jwts
					.parser()
					.setSigningKey(makeKey())
					.parseClaimsJws(token)
					.getBody();
		}catch (ExpiredJwtException e){
			throw new CustomException(HttpStatus.GONE, "토큰 만료");
		}catch (SignatureException | MalformedJwtException e){
			throw new CustomException(HttpStatus.UNAUTHORIZED, "토큰 위조");
		}catch (Exception e){
			throw e;
		}
	}
}
