package br.com.fiap.calorias.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.fiap.calorias.model.Usuarios;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${minha.chave.secreta}")//onde vai esta a palavra chave
    private String palavraSecreta;

    public String gerarToken(Usuarios usuario){

        try {
  
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);

            String token = JWT.create()
                    .withIssuer("calorias")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(gerarDataDeExpiracao())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e){
            throw new RuntimeException("Não foi possível gerar o token!");
        }

    }

    private Instant gerarDataDeExpiracao(){
        return LocalDateTime
                .now()
                //.plusHours(2)  //duas horas
                .plusMinutes(5)  // 5 minutos
                .toInstant(ZoneOffset.of("-03:00"));
    }
    
    

    public String validarToken(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC256(palavraSecreta);

            return JWT.require(algorithm)
                    .withIssuer("calorias")
                    .build()
                    .verify(token)
                    .getSubject();
            
        }catch (JWTVerificationException e){
            return "";
        }
    }



}