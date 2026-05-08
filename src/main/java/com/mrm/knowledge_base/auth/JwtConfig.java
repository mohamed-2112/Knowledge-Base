//package com.mrm.knowledge_base.auth;
//
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.jwk.RSAKey;
//import com.nimbusds.jose.jwk.source.JWKSource;
//import com.nimbusds.jose.proc.SecurityContext;
//import org.springframework.core.io.Resource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
//
//import java.nio.charset.StandardCharsets;
//import java.security.KeyFactory;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Base64;
//
//@Configuration
//public class JwtConfig {
//
//    @Value("${app.jwt.private-key}")
//    private Resource privateKeyResource;
//
//    @Value("${app.jwt.public-key}")
//    private Resource publicKeyResource;
//
//    @Bean
//    public RSAKey rsaKey() throws Exception {
//        RSAPublicKey publicKey = loadPublicKey();
//        RSAPrivateKey privateKey = loadPrivateKey();
//
//        return new RSAKey.Builder(publicKey)
//                .privateKey(privateKey)
//                .keyID("knowledge-base-key-1")
//                .build();
//    }
//
//    @Bean
//    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
//        JWKSet jwkSet = new JWKSet(rsaKey);
//        return (jwkSelector, Context) -> jwkSelector.select(jwkSet);
//    }
//
//    @Bean
//    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
//        return new NimbusJwtEncoder(jwkSource);
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder() throws Exception {
//        return NimbusJwtDecoder.withPublicKey(loadPublicKey()).build();
//    }
//
//    private RSAPrivateKey loadPrivateKey() throws Exception {
//        String key = new String(privateKeyResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
//
//        key = key
//                .replace("-----BEGIN PRIVATE KEY-----", "")
//                .replace("-----END PRIVATE KEY-----", "")
//                .replaceAll("\\s", "");
//
//        byte[] decoded = Base64.getDecoder().decode(key);
//
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//
//        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
//    }
//
//    private RSAPublicKey loadPublicKey() throws Exception {
//        String key = new String(publicKeyResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
//
//        key = key
//                .replace("-----BEGIN PUBLIC KEY-----", "")
//                .replace("-----END PUBLIC KEY-----", "")
//                .replaceAll("\\s", "");
//
//        byte[] decoded = Base64.getDecoder().decode(key);
//
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//
//        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
//    }
//
//}
