package com.holis.san01.db;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DatabaseSelectionFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String db = req.getHeader("X-DB"); // "A" ou "B"

        if (db == null || (!db.equals("A") && !db.equals("B"))) {
            db = "A"; // padrão
        }

        DbContext.setDb(db);

        try {
            chain.doFilter(request, response);
        } finally {
            DbContext.clear();
        }
    }
}

