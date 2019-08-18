package zcq.myjpa.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/09
 */
public class JWTUtils {

    //密钥
    public static String secret;
    //签发方
    public static String issuer;
    //接收方
    public static String aud_pad;

    public static String aud_machine;
    //过期时间
    public static long express;

    //iss: token的发行者
    //sub: token的题目
    //aud（setAudience）: token的客户
    //exp: 经常使用的，以数字时间定义失效期，也就是当前时间以后的某个时间本token失效。
    //nbf: 定义在此时间之前，JWT不会接受处理。
    //iat（setIssuedAt）: JWT发布时间，能用于决定JWT年龄
    //jti（setId）: JWT唯一标识. 能用于防止 JWT重复使用，一次只用一个token
    /**
     * 创建 jwt
     * @param subject
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public static String createJWT(String aud, String subject, long ttlMillis)  {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256 ;
        long nowMillis = System. currentTimeMillis();
        Date now = new Date( nowMillis);
        JwtBuilder builder = Jwts. builder()
                .setId(UUID.randomUUID().toString())
                .setIssuer(issuer)//颁发者
                .setAudience(aud)//接收者
                .setIssuedAt(now)//颁发时间
                .setSubject(subject)
                .signWith(signatureAlgorithm, secret);
        if (ttlMillis >= 0){
            //过期时间
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date( expMillis);
            builder.setExpiration( exp);
        }
        return builder.compact();
    }

    /**
     * 获取jwt token中加密部分的数据
     */
    public static String getSecretToken (String aud, String subject, long ttlMillis) {
        String fullToken = JWTUtils.createJWT(aud, subject, ttlMillis);
        return fullToken.substring(fullToken.lastIndexOf(".")  + 1 );
    }

    /**
     * 解密 jwt
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) {
        Claims claims = Jwts. parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
}
