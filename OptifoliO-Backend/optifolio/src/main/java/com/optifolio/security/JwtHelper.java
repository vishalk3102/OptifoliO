package com.optifolio.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {

    @Value("${jwt_token_validity}")
    public static final long jwt_token_validity=5*60*60;

    @Value("${jwt_secret}")
    private static final String jwt_secret="sgdcsudchshcsicuiseowgeiodsbcoloodoo9ude9hduefhlppcpsdpcsww";


    //Retrieves the username from the JWT token by extracting the 'subject' claim.
    public String getUsernameFromToken(String token)
    {
        return getClaimFromToken(token,Claims::getSubject);
    }


    //Retrieves the expiration date from the JWT token by extracting the 'expiration' claim.
    public Date getExpirationDateFromToken(String token)
    {
        return getClaimFromToken(token,Claims::getExpiration);
    }


    //Generates a secret key for signing the JWT using the secret string.
    private SecretKey getSigningKey()
    {
        byte[] keyBytes=jwt_secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    //Retrieves a specific claim from the JWT token using a function that applies to the Claims.
    private <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //Retrieves all claims from the JWT token. Requires the secret key to parse the token.
    private Claims getAllClaimsFromToken(String token)
    {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }


    //Checks if the JWT token has expired.
    private Boolean isTokenExpired(String token)
    {
        final Date expiration=getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //Generates a JWT token for a user with their details.
    public String generateToken(UserDetails userDetails, String userId)
    {
        Map<String, Object> claims=new HashMap<>();
        claims.put("userId",userId);
        return createToken(claims,userDetails.getUsername());
    }


    //Creates a JWT token with specified claims and subject (username).
    private String createToken(Map<String,Object> claims,String subject)
    {
        return  Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+jwt_token_validity*1000)).signWith(getSigningKey()).compact();
    }


    //Validates the JWT token by checking if the username from the token matches the userDetails and if the token is not expired.
    public Boolean validateToken(String token,UserDetails userDetails)
    {
        final  String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
