package by.shibaev.jdbc.model.util;

import java.util.UUID;

public class IDGenerator {
    public static String generate() {
        UUID newId = UUID.randomUUID();
        return newId.toString();
    }
}