package com.basejava.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Введенное резюме " + uuid + " уже существует! Укажите другое резюме.", uuid);
    }
}
