package com.dav.spring.hibernate.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.dav.spring.hibernate.common.util.Constants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// TODO: Auto-generated Javadoc
/**
 * The Class TokenHandler.
 */
@Component()
public class TokenHandler {

	/** The secret. */
	private final String secret= "DAV_SERVER";

	private static List<String> tokens;

	public TokenHandler() {
		tokens = new ArrayList<String>();
	}

	/**
	 * Parses the user from token.
	 *
	 * @param token the token
	 * @return the string
	 * @throws TokenHandlerError
	 */
	public String parseUserFromToken(String token) throws TokenHandlerError {
		String result = Constants.STR_BLANK;
		if (tokens.contains(token)) {
			try {
				result = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
			} catch (Exception e) {
				tokens.remove(token);
				SecurityContextHolder.getContext().setAuthentication(null);
				throw new TokenHandlerError("Token time out");
			}
		}
		return result;
	}

	/**
	 * Creates the token for user.
	 *
	 * @param username the username
	 * @return the string
	 */
	public String createTokenForUser(String username) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + TimeUnit.HOURS.toMillis(1L));

		String token = Jwts.builder()
						.setId(UUID.randomUUID().toString())
						.setSubject(username)
						.setIssuedAt(now)
						.setExpiration(expiration)
						.signWith(SignatureAlgorithm.HS512, secret)
						.compact();

		tokens.add(token);
		return token;
	}
}
