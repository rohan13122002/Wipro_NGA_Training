package com.example.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.Map;

	@Service
	public class JwtService {
	    private final Key key;
	    private final long expirationMillis;

	    public JwtService(@Value("${jwt.secret}") String secret,
	                      @Value("${jwt.expirationMillis}") long expirationMillis) {
	        this.key = Keys.hmacShaKeyFor(secret.getBytes());
	        this.expirationMillis = expirationMillis;
	    }

	    public String generateToken(String username, String role) {
	        Date now = new Date();
	        Date exp = new Date(now.getTime() + expirationMillis);
	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(now)
	                .setExpiration(exp)
	                .addClaims(Map.of("role", role))
	                .signWith(key, SignatureAlgorithm.HS256)
	                .compact();
	    }

	    public String extractUsername(String token) {
	        return parse(token).getBody().getSubject();
	    }

	    public String extractRole(String token) {
	        Object role = parse(token).getBody().get("role");
	        return role == null ? null : role.toString();
	    }

	    public boolean isValid(String token, String username) {
	        try {
	            return extractUsername(token).equals(username) && !isExpired(token);
	        } catch (JwtException e) {
	            return false;
	        }
	    }

	    private boolean isExpired(String token) {
	        Date exp = parse(token).getBody().getExpiration();
	        return exp.before(new Date());
	    }

	    private Jws<Claims> parse(String token) {
	        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
	    }
	}


