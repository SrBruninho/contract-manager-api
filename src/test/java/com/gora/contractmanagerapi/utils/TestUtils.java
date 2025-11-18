package com.gora.contractmanagerapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

public class TestUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String objectToJson(Object value) throws Exception {
        return objectMapper.writeValueAsString(value);
    }

    public static <T> T getIdFromJson(MvcResult json, Class<T> clazz) throws UnsupportedEncodingException, JsonProcessingException {
        String jsonResponse = json.getResponse().getContentAsString();
        return objectMapper.readValue(jsonResponse, clazz);
    }

    public static <T> T convertResultToObject(MvcResult result, Class<T> clazz)
            throws UnsupportedEncodingException {
        String json = result.getResponse().getContentAsString();
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error while converting Json to " + clazz.getSimpleName(), e);
        }
    }

    public static <T> T convertResultToObject(MvcResult result, TypeReference<T> typeReference)
            throws UnsupportedEncodingException {
        String json = result.getResponse().getContentAsString();
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Error while converting Json to a generic type", e);
        }
    }

    public static JsonNode removeEmbedded(String json, String modelListName) throws JsonProcessingException {
        return objectMapper
                .readTree(json)
                .path("_embedded")
                .path(modelListName);
    }

    public static ObjectMapper getObjectMapper(){
        return objectMapper;
    }

}
