package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void saveUuid(Resume resume) {
        int index = Math.abs(getIndex(resume.getUuid()) + 1);
        if (storage[index] != null) {
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        storage[index] = resume;
        size++;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
