package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    protected void saveToArray(Resume resume, int index) {
        index = Math.abs(index + 1);
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        // Arrays.binarySearch возвращает позицию заданного значения
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
