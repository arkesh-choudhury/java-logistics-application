package com.example.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// T indicates that it will work on all datatypes
public class JsonStorage<T> {
    private final String filePath;
    private final ObjectMapper objectMapper;
    private final TypeReference<List<T>> typeReference;

    // type reference aids in extracting the models back from the json
    public JsonStorage(String filePath, TypeReference<List<T>> typeReference) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();
        this.typeReference = typeReference;
    }

    public List<T> loadFromJson() {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        // exception handling
        try {
            return objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveToJson(List<T> data) {
        try {
            objectMapper.writeValue(new File(filePath), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
