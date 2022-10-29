package org.login.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import org.login.exception.JwtException;
import org.login.model.UserLogin;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

public class JwtUtil {

    public String generateToken(UserLogin user) {

        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES256;
            HashMap<String, Object> header = new HashMap<String, Object>();
            header.put("alg", signatureAlgorithm.toString());
            header.put("typ", "JWT");

            JwtBuilder tokenJWT = Jwts
                    .builder()
                    .setHeader(header)
                    .setId("1")
                    .setSubject("http://nisum.com")
                    .claim("name", user.getName())
                    .claim("mail", user.getMail())
                    .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
                    .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
                    .signWith(SignatureAlgorithm.HS256, "secretkey".getBytes());

            String tokenJWTString = tokenJWT.compact();
            System.out.println(tokenJWTString);
            return tokenJWTString;

        } catch (Exception ex) {

            return "Error creating the token JWT" + ex.getMessage();
        }
    }

    public void decodeToken(String token){

        String[] chunks = token.split("\\.");

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        String key ="secretkey";
        SignatureAlgorithm sa = SignatureAlgorithm.HS256;
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), sa.getJcaName());
        String tokenWithoutSignature = chunks[0] + "." + chunks[1];
        String signature = chunks[2];
        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, secretKeySpec);

        if (!validator.isValid(tokenWithoutSignature, signature)) {
            throw new JwtException();
        }

    }
}
