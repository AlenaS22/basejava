package com.basejava.exception;

public class StorageException extends RuntimeException {
    // резюме, с которым случились проблемы
    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
