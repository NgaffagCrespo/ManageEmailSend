package booksmanagement.nextGenTest.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

        //Dans le code qui suit nous utilisons un algorithme pour generer la signature du web token
        Algorithm algorithm = Algorithm.HMAC256("SecretToAccessWebServiceForBooksManagement");

        //Ici on genere le jwt Token
        String jwtAcessToken =
                JWT.create()
                        .withSubject(user.getUsername())
                                .withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
                                        .withIssuer(request.getRequestURL().toString())
                                                .withClaim("roles",user.getAuthorities().stream().map(grantedAuthority -> {
                                                    return grantedAuthority.getAuthority(); // Ici on reconvertit la liste de Granted Autorities en une liste de String
                                                }).collect(Collectors.toList()))
                                                        .sign(algorithm);

        String jwtAcessRefreshToken =
                JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+60*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algorithm);

        Map<String,String> tokenId = new HashMap<>();
        tokenId.put("Acess-Token",jwtAcessToken);
        tokenId.put("Refresh-Token",jwtAcessRefreshToken);
        //ici on indique que le corps de la response sera au format
        response.setContentType("application/json");
        //ici on envoit le tokenId en format json dans le corps de la response
        new ObjectMapper().writeValue(response.getOutputStream(),tokenId);
    }
}
