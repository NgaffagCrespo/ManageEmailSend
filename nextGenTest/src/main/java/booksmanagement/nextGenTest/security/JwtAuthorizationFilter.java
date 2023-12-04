package booksmanagement.nextGenTest.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JwtAuthorizationFilter extends OncePerRequestFilter {


    //Ceci nous permettra de verifier l'authenticite du token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationToken = request.getHeader("Authorization");

        if (authorizationToken!=null && authorizationToken.startsWith("Bearer ")){

            try{

                String theToken = authorizationToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("SecretToAccessWebServiceForBooksManagement");
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                //verification du token
                DecodedJWT decodedJWT = jwtVerifier.verify(theToken);
                String username = decodedJWT.getSubject();
                String[] roles = decodedJWT.getClaim("roles").asArray(String.class);

                Collection<GrantedAuthority> authorities = new ArrayList<>();
                for (String r:roles){
                    authorities.add(new SimpleGrantedAuthority(r));
                }

                //Cet objet defini l'utilisateur authentifie
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(username,null,authorities);
                // Ici nous allons authentifie l'utilisateur en fonction de ses differents droits
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                filterChain.doFilter(request,response);

            }catch (Exception e){

                response.setHeader("error-message",e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);

            }

        }else {
            filterChain.doFilter(request,response);
        }

    }
}
