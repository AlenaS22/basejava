package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(r.toString())) {
                printMessage("save1");
                return;
            }
        }
        if (size == storage.length) {
            printMessage("save2");
            return;
        }
        storage[size++] = r;
    }

    public void update(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(r.toString())) {
                storage[i].setUuid("uuid_update");
                return;
            }
        }
        printMessage("update");
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].toString())) {
                return storage[i];
            }
        }
        printMessage("get");
        return null;
    }

    public int getIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].toString())) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            printMessage("delete");
            return;
        }
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void printMessage(String operation) {
        if (operation.equals("update") || operation.equals("get") || operation.equals("delete")) {
            System.out.println("Резюме не найдено! Укажите существующее резюме.");
            return;
        }
        if (operation.equals("save1")) {
            System.out.println("Введенное резюме уже существует! Укажите другое резюме.");
            return;
        }
        if (operation.equals("save2")) {
            System.out.println("Резюме невозможно сохранить! Хранилище переполнено. Обратитесь к администратору.");
        }
    }
}
