package com.gora.contractmanagerapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
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
}
