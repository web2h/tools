package com.web2h.tools.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

public class JwtToolsUnitTest {

	private static final String SECRET_KEY = "secret.key";

	private static final String OTHER_SECRET_KEY = "other.secret.key";

	private static final String JWT_ID = "jwt.id";

	private static final String JWT_ISSUER = "jwt.issuer";

	private static final String JWT_SUBJECT = "jwt.subject";

	private static final long JWT_LONG_LIFE_TIME = 1000000;

	private static final long JWT_SHORT_LIFE_TIME = 400;

	private static final String INVALID_JWT = "{not.a.jwt}";

	@Test
	public void givenInvalidJwtWhenDecodeJwtThenMalformedJwtExceptionIsThrown() {

		// Given
		String jwt = INVALID_JWT;

		// When
		MalformedJwtException thrown = assertThrows(MalformedJwtException.class,
				() -> JwtTools.decodeJwt(SECRET_KEY, jwt));

		// Then
		assertTrue(thrown.getMessage().contains("Unable to read"));
	}

	@Test
	public void givenExpiredJwtWhenDecodeJwtThenExpiredJwtExceptionIsThrown() throws InterruptedException {

		// Given
		String jwt = JwtTools.createJwt(SECRET_KEY, JWT_ID, JWT_ISSUER, JWT_SUBJECT, JWT_SHORT_LIFE_TIME);
		Thread.sleep(JWT_SHORT_LIFE_TIME + 10);

		// When
		ExpiredJwtException thrown = assertThrows(ExpiredJwtException.class,
				() -> JwtTools.decodeJwt(SECRET_KEY, jwt));

		// Then
		assertTrue(thrown.getMessage().contains("JWT expired"));
	}

	@Test
	public void givenValidJwtAndDifferentKeyWhenDecodeJwtThenSignatureExceptionIsThrown() {

		// Given
		String jwt = JwtTools.createJwt(SECRET_KEY, JWT_ID, JWT_ISSUER, JWT_SUBJECT, JWT_LONG_LIFE_TIME);

		// When
		SignatureException thrown = assertThrows(SignatureException.class,
				() -> JwtTools.decodeJwt(OTHER_SECRET_KEY, jwt));

		// Then
		assertTrue(thrown.getMessage().contains("JWT signature does not match locally computed signature"));
	}

	@Test
	public void givenTamperedJwtAndDifferentKeyWhenDecodeJwtThenSignatureExceptionIsThrown() {

		// Given
		String jwt = JwtTools.createJwt(SECRET_KEY, JWT_ID, JWT_ISSUER, JWT_SUBJECT, JWT_LONG_LIFE_TIME) + "XX";

		// When
		SignatureException thrown = assertThrows(SignatureException.class,
				() -> JwtTools.decodeJwt(OTHER_SECRET_KEY, jwt));

		// Then
		assertTrue(thrown.getMessage().contains("JWT signature does not match locally computed signature"));
	}

	@Test
	public void givenValidJwtWhenDecodeJwtThenClaimsAreValid() {

		// Given
		String jwt = JwtTools.createJwt(SECRET_KEY, JWT_ID, JWT_ISSUER, JWT_SUBJECT, JWT_LONG_LIFE_TIME);

		// When
		Claims claims = JwtTools.decodeJwt(SECRET_KEY, jwt);

		// Then
		assertEquals(JWT_ID, claims.getId());
		assertEquals(JWT_ISSUER, claims.getIssuer());
		assertEquals(JWT_SUBJECT, claims.getSubject());
		long elapsedTime = System.currentTimeMillis() - claims.getIssuedAt().getTime();
		assertTrue(elapsedTime < 5000);
	}
}
