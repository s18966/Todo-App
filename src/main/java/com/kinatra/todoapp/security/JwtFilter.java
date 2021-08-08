package com.kinatra.todoapp.security;

import com.kinatra.todoapp.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtFilter extends GenericFilterBean {
    //header in http request where token will be placed
    public static final String HEADER = "Authorization";
    private JwtProvider jwtProvider;
    private UserService userService;
    @Autowired
    public JwtFilter(JwtProvider provider,UserService service){
        this.jwtProvider = provider;
        this.userService = service;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = getTokenFromHttpRequest((HttpServletRequest) servletRequest);
        if(token!=null && jwtProvider.isTokenValid(token)){
            String login = jwtProvider.extractLoginFromToken(token);
            UserDetails user = userService.loadUserByUsername(login);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
    private String getTokenFromHttpRequest(HttpServletRequest req){
        String auth = req.getHeader(HEADER);
        if(auth!=null && auth.startsWith("Bearer ")){
            //get token after word Bearer
            return auth.substring(7);
        }
        return null;
    }

}
