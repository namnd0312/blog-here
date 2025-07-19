package com.namnd.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);
    private static final String LOG_ID = "X-Request-Log-Id";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String uuid = UUID.randomUUID().toString();
        MDC.put("traceId", uuid); // Gắn vào MDC

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
        wrappedResponse.setHeader(LOG_ID, uuid);



        long startTime = System.currentTimeMillis();
        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);

            String reqBody = getBody(wrappedRequest.getContentAsByteArray(), request.getCharacterEncoding());
            log.info("Request ---> [{}] --> {} {} | Body: {}", uuid, request.getMethod(), request.getRequestURI(), reqBody);
        } finally {
            long duration = System.currentTimeMillis() - startTime;

            String resBody = getBody(wrappedResponse.getContentAsByteArray(), response.getCharacterEncoding());
            log.info("Response ---> [{}] <-- {} | Duration: {}ms | Body: {}", uuid, response.getStatus(), duration, resBody);

            MDC.clear();
            wrappedResponse.copyBodyToResponse();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/webjars")
                || path.endsWith(".css")
                || path.endsWith(".js")
                || path.endsWith(".ico")
                || path.contains("actuator");
    }

    private String getBody(byte[] content, String encoding) {
        try {
            if (content.length == 0) return "[empty]";
            return new String(content, encoding);
        } catch (UnsupportedEncodingException e) {
            return "[unknown encoding]";
        }
    }
}
