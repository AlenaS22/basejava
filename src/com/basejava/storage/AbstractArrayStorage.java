package com.basejava.storage;

import com.basejava.exception.StorageException;
import com.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Резюме невозможно сохранить! Хранилище переполнено. Обратитесь к администратору.", resume.getUuid());
        }
        saveToArray(resume, (int) index);
        size++;
    }

    protected abstract void saveToArray(Resume resume, int index);

    @Override
    protected void updateResume(Resume resume, Object index) {
        storage[(int) index] = resume;
    }

    @Override
    protected Resume getResume(Object index) {
        return storage[(int) index];
    }

    @Override
    protected void deleteResume(Object index) {
        int numMoved = size - 1 - (int) index;
        if (numMoved > 0) {
            System.arraycopy(storage, (int) index + 1, storage, (int) index, numMoved);
        }
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
   /* *@Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }*/

    @Override
    public List<Resume> getCopyStorage() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    public int size() {
        return size;
    }
}