package io.github.darioteles.security.jwt;

import io.github.darioteles.VendasApplication;
import io.github.darioteles.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

/**
 * Define um classe Service Spring responsável pelos serviços JWT.
 */
@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${securtiy.jwt.chave-assinatura}")
    private String chaveAssinatura;

    /**
     * Gera um token baseado no tempo de expiração, chave e informações do usuario.
     * @param usuario
     * @return token
     */
    public String gerarToken(Usuario usuario){
        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);

        Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());

        //Adicionando informações customizadas no Token
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("emaildousuario", "usuario@gmail.com");
        claims.put("roles", "admin");

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    /**
     *  Extrai os claims de um token.
     * @param token
     * @return Claims
     * @throws ExpiredJwtException
     */
    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Verifica se o token está validado fora do tempo de expiração.
     * @param token
     * @return boolean
     */
    public boolean tokenValido (String token){
        try {
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now().isAfter(data);
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Extrai o login do usuário salvo no Subject do token .
     * @param token
     * @return
     * @throws ExpiredJwtException
     */
    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        return (String) obterClaims(token).getSubject();
    }

/*    //Teste da classe
    public static void main(String[] args){
        ConfigurableApplicationContext contexto = SpringApplication.run(VendasApplication.class);
        JwtService service = contexto.getBean(JwtService.class);
        Usuario usuario = Usuario.builder().login("fulano").build();
        String token = service.gerarToken(usuario);
        System.out.println(token);

        boolean isTokenValido = service.tokenValido(token);
        System.out.println("O token está válido? " + isTokenValido);

        System.out.println(service.obterLoginUsuario(token));

    }*/
}
