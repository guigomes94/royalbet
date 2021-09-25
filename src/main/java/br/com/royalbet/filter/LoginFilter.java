package br.com.royalbet.filter;

import br.com.royalbet.models.User;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter{
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException{
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpRequest.getSession(false);

        String uri = httpRequest.getRequestURI();
        if(httpSession != null){
            User loginUser = (User) httpSession.getAttribute("user");
            if(loginUser == null){
                redirectLogin(httpRequest, httpResponse, uri);
                return;
            } else{
                chain.doFilter(request, response);
            }
        } else {
            redirectLogin(httpRequest, httpResponse, uri);
        }
    }

    private void redirectLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String uri) throws IOException {
        String baseUrl = httpRequest.getContextPath();
        String paginaLogin = baseUrl + "/login";
        httpResponse.sendRedirect(httpResponse.encodeRedirectURL(paginaLogin));

    }
    @Override
    public void init(FilterConfig arg0) throws ServletException{
    }
    @Override
    public void destroy(){

    }
}
