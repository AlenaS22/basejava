package com.basejava.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Резюме " + uuid + " не найдено! Укажите существующее резюме.", uuid);
    }
}
