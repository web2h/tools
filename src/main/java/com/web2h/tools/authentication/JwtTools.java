package com.web2h.tools.authentication;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTools {

	public static String createJwt(String appSecretKey, String id, String issuer, String subject) {

		return createJwt(appSecretKey, id, issuer, subject, 0);
	}

	public static String createJwt(String appSecretKey, String id, String issuer, String subject,
			long jwtLifeTimeInMs) {

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(appSecretKey);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id)
				.setIssuedAt(now)
				.setSubject(subject)
				.setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		// if it has been specified, let's add the expiration
		if (jwtLifeTimeInMs > 0) {
			long expMillis = nowMillis + jwtLifeTimeInMs;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	public static Claims decodeJwt(String appSecretKey, String jwt) {
		// This line will throw an exception if it is not a signed JWS (as expected)
		Claims claims = Jwts.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary(appSecretKey))
				.parseClaimsJws(jwt).getBody();
		return claims;
	}
}