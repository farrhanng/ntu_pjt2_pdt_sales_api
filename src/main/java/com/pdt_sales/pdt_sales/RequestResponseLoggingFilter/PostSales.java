package com.pdt_sales.pdt_sales.RequestResponseLoggingFilter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
public class PostSales {

  private static final Logger LOGGER = LoggerFactory.getLogger(PostSales.class);

  public void logSalesCreation(ContentCachingRequestWrapper requestWrapper,
      ContentCachingResponseWrapper responseWrapper) {
    try {
      // Log the response body
      String responseBody = new String(responseWrapper.getContentAsByteArray(), responseWrapper.getCharacterEncoding());

      // Parse the JSON response body using Jackson
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(responseBody);

      // Extract the "salesId" field from the JSON
      int salesId = rootNode.get("salesId").asInt();

      // Log the "salesId" in the desired format
      LOGGER.info("Sales ID {} created", salesId);
    } catch (Exception e) {
      LOGGER.error("Error logging sales creation: {}", e.getMessage(), e);
    }
  }
}
