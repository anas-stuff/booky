package com.anas.booky.api.booky.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * Service class for handling JWT operations such as token generation, validation, and extraction of claims.
 */
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token.duration}")
    private long tokenDuration; // In hours

    /**
     * Extracts the email (subject) from the JWT.
     *
     * @param jwt the JWT token
     * @return the email (subject) extracted from the token
     */
    public String extractEmail(final String jwt) {
        return this.extractClaim(jwt, Claims::getSubject);
    }

    /**
     * Extracts all claims from the JWT.
     *
     * @param jwt the JWT token
     * @return the claims extracted from the token
     */
    public Claims extractAllClaims(final String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getSingingKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * Extracts a specific claim from the JWT using a resolver function.
     *
     * @param jwt      the JWT token
     * @param resolver the function to resolve the claim
     * @param <T>      the type of the claim
     * @return the claim extracted from the token
     */
    public <T> T extractClaim(final String jwt, final Function<Claims, T> resolver) {
        final var claims = this.extractAllClaims(jwt);
        return resolver.apply(claims);
    }

    /**
     * Generates a JWT token for the given user details.
     *
     * @param userDetails the user details
     * @return the generated JWT token
     */
    public String generateToken(final UserDetails userDetails) {
        return this.generateToken(Map.of(), userDetails);
    }

    /**
     * Generates a JWT token with additional claims for the given user details.
     *
     * @param claims      additional claims to include in the token
     * @param userDetails the user details
     * @return the generated JWT token
     */
    public String generateToken(final Map<String, Object> claims, final UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .signWith(this.getSingingKey(), SignatureAlgorithm.HS256)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.tokenDuration * 3600 * 1000))
                .compact();
    }

    /**
     * Checks if the JWT token is expired.
     *
     * @param jwt the JWT token
     * @return true if the token is expired, false otherwise
     */
    public boolean isTokenExpired(final String jwt) {
        return this.extractExpiration(jwt).before(new Date());
    }

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param jwt the JWT token
     * @return the expiration date extracted from the token
     */
    public Date extractExpiration(final String jwt) {
        return this.extractClaim(jwt, Claims::getExpiration);
    }

    /**
     * Validates the JWT token against the user details.
     *
     * @param jwt         the JWT token
     * @param userDetails the user details
     * @return true if the token is valid, false otherwise
     */
    public boolean isTokenValid(final String jwt, final UserDetails userDetails) {
        final var email = this.extractEmail(jwt);
        return email.equals(userDetails.getUsername()) && !this.isTokenExpired(jwt);
    }

    /**
     * Retrieves the signing key used for signing the JWT token.
     *
     * @return the signing key
     */
    private Key getSingingKey() {
        final var key = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(key);
    }
}