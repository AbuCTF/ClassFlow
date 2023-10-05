/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.attendancetracker.auth;

/**
 *
 * @author USER
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AuthHandler {
    // The location of the file containing the secret key
    private static final String SECRET_KEY_FILE_PATH = "secret_key.txt";
    
    // Token expiration time (e.g., 1 hour)
    private static final long EXPIRATION_TIME = 3600000; 
    
    private static final Set<String> revokedTokens = ConcurrentHashMap.newKeySet();

    public static String generateToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        String authToken = Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS256, getSecretKey())
            .compact();

        return authToken;
    }

    public static boolean validateToken(String token) {
        try {
            if (revokedTokens.contains(token)) {
                return false; // Token is revoked
            }
            Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token);
            return true; // Token is valid
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            // Token validation failed
            return false;
        }
    }

    public static void revokeToken(String token) {
        revokedTokens.add(token);
    }

    public static String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                .setSigningKey(getSecretKey())
                .parseClaimsJws(token)
                .getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            // Token validation failed
            return null;
        }
    }

    private static String getSecretKey() {
        try {
            return new String(Files.readAllBytes(Paths.get(SECRET_KEY_FILE_PATH)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
