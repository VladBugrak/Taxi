package com.taxiservice.controllers.filters;

import com.taxiservice.utils.LocaleThreadLocal;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {
    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String locale = req.getParameter("sessionLocale");

        if (locale != null) {
            req.getSession().setAttribute("lang", locale);
            LocaleThreadLocal.setLocale(Locale.forLanguageTag(locale));
        }
        else
            LocaleThreadLocal.setLocale(Locale.forLanguageTag(Optional
                    .ofNullable((String) req.getSession().getAttribute("lang"))
                    .orElse(Locale.getDefault().getLanguage())));

        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {}

}
