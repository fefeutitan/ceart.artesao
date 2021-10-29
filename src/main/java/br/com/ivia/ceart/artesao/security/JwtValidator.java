package br.com.ivia.ceart.artesao.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import br.com.ivia.ceart.artesao.to.PessoaTO;

@Component
public class JwtValidator {

	static final String SECRET = "St@CeartCe";

    public PessoaTO validate(String token) {
    	PessoaTO pessoa = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            pessoa = new PessoaTO();
            pessoa.setCpf(body.getSubject());
            pessoa.setSenha((String) body.get("senha"));
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return pessoa;
    }
}
