package com.out.android.util;

import com.out.android.exception.CustomException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
	@Value("${jwt.auth.access}")
	String ACCESS_KEY;

	public String encodingToken(Long idx){
		try{
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			Key signingKey = makeKey();
			Map<String, Object> claims = new HashMap<>();
			claims.put("idx", idx);

			return Jwts.builder()
					.setClaims(claims)
					.setExpiration(new Date(System.currentTimeMillis() + 30L *24*60*60*1000))
					.signWith(signatureAlgorithm,signingKey)
					.compact();
		}catch (CustomException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "토큰 생성 에러");
		}
	}

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
			e.printStackTrace();
			throw new CustomException(HttpStatus.GONE, "토큰 만료");
		}catch (SignatureException | MalformedJwtException e){
			e.printStackTrace();
			throw new CustomException(HttpStatus.UNAUTHORIZED, "토큰 위조");
		}catch (Exception e){
			throw e;
		}
	}
}
