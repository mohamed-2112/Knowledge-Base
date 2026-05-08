//package com.mrm.knowledge_base.auth;
//
//import com.mrm.knowledge_base.user.User;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
//import org.springframework.security.oauth2.jwt.JwsHeader;
//import org.springframework.security.oauth2.jwt.JwtClaimsSet;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.time.Instant;
//
//@Service
//public class JwtService {
//    private final JwtEncoder jwtEncoder;
//
//    @Value("${app.jwt.expiration-seconds}")
//    private long expirationSeconds;
//
//    public JwtService(JwtEncoder jwtEncoder) {
//        this.jwtEncoder = jwtEncoder;
//    }
//
//    public String generateToken(User user){
//        Instant now = Instant.now();
//        JwtClaimsSet claims = JwtClaimsSet.builder()
//                .issuer("knowledge-base-api")
//                .subject(user.getEmail())
//                .claim("userId", user.getId())
//                .claim("role", user.getRole().name())
//                .issuedAt(now)
//                .expiresAt(now.plusSeconds(expirationSeconds))
//                .build();
//        JwsHeader header = JwsHeader.with(SignatureAlgorithm.RS256).build();
//
//        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
//    }
//
//}
