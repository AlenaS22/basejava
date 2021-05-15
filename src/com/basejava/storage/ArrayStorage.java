package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) != -1) {
            printMessage("save1");
            return;
            }
        if (size == storage.length) {
            printMessage("save2");
            return;
        }
        storage[size++] = resume;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            storage[index].setUuid("uuid_update");
            return;
        }
        printMessage("update");
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
            }
        printMessage("get");
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            printMessage("delete");
            return;
        }
        if (size - 1 - index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        }
        storage[size - 1] = null;
        size--;
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
