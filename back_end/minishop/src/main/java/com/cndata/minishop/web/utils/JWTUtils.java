package com.cndata.minishop.web.utils;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;    

public class JWTUtils {

	public static String createJWT(String id, String issuer, String subject, long ttlMillis, String secret) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		JwtBuilder builder = Jwts.builder().setId(id)
		                                .setIssuedAt(now)
		                                .setSubject(subject)
		                                .setIssuer(issuer)
		                                .signWith(signatureAlgorithm, signingKey);
		//指定过期时间（毫秒）
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}
		return builder.compact();
	}
	
	public static Map<String,Object> parseJWT(String jwt, String secret) {
		Map<String,Object> map = new HashMap<String,Object>();
		Claims claims = Jwts.parser()
		   .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
		   .parseClaimsJws(jwt).getBody();
		map.put("ID", claims.getId());
		map.put("Subject", claims.getSubject());
		map.put("Issuer", claims.getIssuer());
		map.put("Expiration", claims.getExpiration());
		return map;
	}
}
