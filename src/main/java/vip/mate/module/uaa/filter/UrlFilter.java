//package vip.mate.module.uaa.filter;
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponseWrapper;
//import java.io.IOException;
//
///**
// * @author pangu
// */
//@Slf4j
//public class UrlFilter implements Filter {
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper((HttpServletResponse) response);
//
//        String path = httpRequest.getRequestURI();
//
//        if (path.indexOf("oauth") > 0) {
//            path = path.replace("/mate-uaa", "");
//            log.error("path:{}", path);
//            httpRequest.getRequestDispatcher(path).forward(request,response);
//        } else {
//            chain.doFilter(request,response);
//        }
//    }
//}
