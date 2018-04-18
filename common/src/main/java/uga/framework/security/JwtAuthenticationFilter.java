package uga.framework.security;

import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private static final String AUTHORIZATION_BEARER_STARTER = "Bearer ";
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorization = request.getHeader("Authorization");
        if(StringUtils.isNotEmpty(authorization) && authorization.startsWith(AUTHORIZATION_BEARER_STARTER)) {
            SecurityContextHolder.getContext().setAuthentication(fetchAuthentication(authorization));
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken fetchAuthentication(String authorization) {
        String user = Jwts.parser()
                .setSigningKey("JwtSecretKey")
                .parseClaimsJws(authorization.replace(AUTHORIZATION_BEARER_STARTER, ""))
                .getBody()
                .getSubject();
        if(StringUtils.isNotEmpty(user))
            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        return null;
    }
}
