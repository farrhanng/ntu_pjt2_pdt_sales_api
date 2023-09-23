package com.pdt_sales.pdt_sales.RequestResponseLoggingFilter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.pdt_sales.pdt_sales.exception.ProductNotFoundException;
import com.pdt_sales.pdt_sales.exception.SalesNotFoundException;

@Component
public class LoggingFilter extends OncePerRequestFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

  @Autowired
  private PostSales putSales;

  @Autowired
  private PostProducts putProducts;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String requestURI = request.getRequestURI();
    String resourceIdentifier = getResourceIdentifierFromURI(requestURI);

    LOGGER.info("Request Method: {}; Resource: {}", request.getMethod(), resourceIdentifier);

    try {
      // Cache the request body for later use
      ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
      ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

      filterChain.doFilter(requestWrapper, responseWrapper);

      if ("POST".equals(request.getMethod())) {
        if (requestURI.equals("/sales")) {
          putSales.logSalesCreation(requestWrapper, responseWrapper);
        } else if (requestURI.equals("/products")) {
          putProducts.logProductCreation(requestWrapper, responseWrapper);
        }
      }

      // Ensure the response is sent to the client
      responseWrapper.copyBodyToResponse();

    } catch (SalesNotFoundException e) {
      LOGGER.warn("SalesNotFoundException: {}", e.getMessage());
    } catch (ProductNotFoundException e) {
      LOGGER.warn("ProductNotFoundException: {}", e.getMessage());
    }
  }

  private String getResourceIdentifierFromURI(String requestURI) {
    String[] parts = requestURI.split("/");
    if (parts.length >= 3) {
      String resourceType = parts[1];
      String resourceId = parts[2];
      if ("sales".equals(resourceType)) {
        return "salesID:" + resourceId;
      } else if ("products".equals(resourceType)) {
        return "productID:" + resourceId;
      }
    } else if (parts.length == 2) {
      String resourceType = parts[1];
      if ("sales".equals(resourceType)) {
        return "all sales records";
      } else if ("products".equals(resourceType)) {
        return "all product records";
      }
    }
    // (/products or /sales)
    return "INVALID REQUEST: " + requestURI;
  }
}
