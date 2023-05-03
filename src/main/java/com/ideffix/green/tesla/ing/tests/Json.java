package com.ideffix.green.tesla.ing.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Json {

    public static <T> T fileToObject(String path, TypeReference<T> tr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = new FileInputStream(path);
        return mapper.readValue(is, tr);
    }
}
