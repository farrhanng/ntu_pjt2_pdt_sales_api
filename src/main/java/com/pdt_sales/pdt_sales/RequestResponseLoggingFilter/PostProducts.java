package com.pdt_sales.pdt_sales.RequestResponseLoggingFilter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
public class PostProducts {

  private static final Logger LOGGER = LoggerFactory.getLogger(PostProducts.class);

  public void logProductCreation(ContentCachingRequestWrapper requestWrapper,
      ContentCachingResponseWrapper responseWrapper) {
    try {
      // Log the response body
      String responseBody = new String(responseWrapper.getContentAsByteArray(), responseWrapper.getCharacterEncoding());

      // Parse the JSON response body using Jackson
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode rootNode = objectMapper.readTree(responseBody);

      // Extract the "productKey" field from the JSON
      int productKey = rootNode.get("productKey").asInt();

      // Log the "productKey" in the desired format
      LOGGER.info("Product Key {} created", productKey);
    } catch (Exception e) {
      LOGGER.error("Error logging product creation: {}", e.getMessage(), e);
    }
  }
}
