package com.iotapi.restapp.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * this class will create a JWT for the user to be stored in session
 * 
 * @author ishan.juneja
 *
 */
public class CreateJWT {

	/**
	 * this method will create a JWT for the parameters passed to it
	 * 
	 * @param id
	 *            of the token generated
	 * @param issuer
	 *            of the token generated
	 * @param subject
	 *            is the data in the token
	 * @param ttlMillis
	 *            expiration time of the token
	 * @return the token
	 */
	public String createJWT(String id, String issuer, String subject, long ttlMillis) {

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("ishanjuneja");
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		// if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

}
