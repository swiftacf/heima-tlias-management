package com.zcf;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {


    /*
    * 生成JWT令牌
    * */
    @Test
    public void testGenerateJwt(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 123);
        dataMap.put("name", "zcf");
        String jwt= Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "emNm")
                .addClaims(dataMap)
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000))
                .compact();
        System.out.println(jwt);
    }

    /*
    * 解析JWT令牌
    * */
    @Test
    public void testParseJwt(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiemNmIiwiaWQiOjEyMywiZXhwIjoxNzg2NzEzNTc3fQ.O4oobzrcpl3WO7LTOQYDqomVegWrf-peV_aW2nuOiHY";
        Claims claims = Jwts.parser().setSigningKey("emNm")
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }
}
