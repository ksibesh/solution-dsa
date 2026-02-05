package com.solution.dsa.stripe;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SchemaValidation {

    public boolean validateJsonSchema(String json, String schema) {
        return false;
    }

    private String readFile(String pathStr) throws IOException {
        Path path = Paths.get(pathStr);
        return Files.readString(path);
    }

    public static void main(String[] args) throws FileNotFoundException {

    }
}
