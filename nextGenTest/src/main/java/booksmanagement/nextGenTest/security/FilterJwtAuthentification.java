package booksmanagement.nextGenTest.security;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class FilterJwtAuthentification extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    //Appel de cette methode quand l'utilisateur tente de se connecter
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username,password);

        /*Dans le return ici l'authentication Manager va se charger de faire appel
         a la verification des mots de passe avec UserDetails service */
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    //Appel de cette methode quand l'authentication a reussi
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        /* Une fois que l'utilisateur est authentifie on le recupere ici
        pour l'utiliser comme un objet user de spring security
        * */
        User user = (User) authResult.getPrincipal();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
