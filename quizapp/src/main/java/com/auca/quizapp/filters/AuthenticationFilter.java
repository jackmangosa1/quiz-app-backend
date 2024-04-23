//package com.auca.quizapp.filters;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"})
//public class AuthenticationFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // Initialization code, if needed
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//            throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        HttpSession session = request.getSession(false);
//
//        // Check if the user is already logged in
//        if (session != null && session.getAttribute("userRole") != null) {
//            String userRole = (String) session.getAttribute("userRole");
//            // Redirect based on user role
//            if ("ADMIN".equals(userRole)) {
//                response.sendRedirect(request.getContextPath() + "/admin.jsp");
//                return;
//            } else if ("USER".equals(userRole)) {
//                response.sendRedirect(request.getContextPath() + "/index.jsp");
//                return;
//            }
//        }
//
//        // If user is not logged in or doesn't have a role, continue to login
//        filterChain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//        // Cleanup code, if needed
//    }
//}
