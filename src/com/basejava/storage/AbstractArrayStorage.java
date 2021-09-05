package com.basejava.storage;

import com.basejava.exception.StorageException;
import com.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void saveResume(Resume resume, int index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Резюме невозможно сохранить! Хранилище переполнено. Обратитесь к администратору.", resume.getUuid());
        }
        saveToArray(resume, index);
        size++;
    }

    protected abstract void saveToArray(Resume resume, int index);

    protected void updateResume(Resume resume, int index) {
        storage[index] = resume;
    }

    protected Resume getResume(int index, String uuid) {
        return storage[index];
    }

    protected void deleteResume(int index, String uuid) {
        int numMoved = size - 1 - index;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
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
}
