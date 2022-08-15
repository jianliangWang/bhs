package com.wjl.system.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "icreator.jwt")
public class JWTUtil {

    private long expire;

    private String secret;

    private String header;

    /**
     * 生成token
     * @return
     */
    public String generateToken(String username) {
        Date date = new Date();
        Date expireDate = new Date(date.getTime() + expire * 1000);

        String token = Jwts.builder().setHeaderParam("type", "JWT").setSubject(username).setIssuedAt(date)
            .setExpiration(expireDate).signWith(
                SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 解析jwt
     *
     * @param jwt
     * @return
     */
    public Claims getClaimsByToken(String jwt) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    /**
     * token是否过期了
     * @param claims
     * @return
     */
    public boolean tokenExpire(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
