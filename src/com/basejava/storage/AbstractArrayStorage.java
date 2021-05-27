package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) >= 0) {
            printMessage("save1", resume.getUuid());
            return;
        }
        if (size == STORAGE_LIMIT) {
            printMessage("save2", resume.getUuid());
            return;
        }
        saveResume(resume, getIndex(resume.getUuid()));
        size++;
    }

    protected abstract void saveResume(Resume resume, int index);

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
            return;
        }
        printMessage("update", resume.getUuid());
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        printMessage("get", uuid);
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            printMessage("delete", uuid);
            return;
        }
        if (size - 1 - index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        }
        storage[size - 1] = null;
        size--;
    }

    protected abstract int getIndex(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected void printMessage(String operation, String uuid) {
        if (operation.equals("update") || operation.equals("get") || operation.equals("delete")) {
            System.out.println("Резюме " + uuid + " не найдено! Укажите существующее резюме.");
            return;
        }
        if (operation.equals("save1")) {
            System.out.println("Введенное резюме " + uuid + " уже существует! Укажите другое резюме.");
            return;
        }
        if (operation.equals("save2")) {
            System.out.println("Резюме невозможно сохранить! Хранилище переполнено. Обратитесь к администратору.");
        }
    }
}
