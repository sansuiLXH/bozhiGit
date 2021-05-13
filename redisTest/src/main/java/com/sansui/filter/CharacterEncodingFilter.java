package com.sansui.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/12 9:29
 * @modified By  西西里_SanSui in 2021/5/12 9:29
 * @description AddDescriptionHere
 */
public class CharacterEncodingFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
