/*ArrayList*/
package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        storage.add(resume);
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        storage.set((Integer) index, resume);
    }

    @Override
    protected Resume getResume(
            Object index) {
        return storage.get((Integer) index);
    }

    @Override
    protected void deleteResume(Object index) {
        storage.remove((int) index);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        int index = -1;
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public List<Resume> getStorageAsList() {
        return new ArrayList<>(storage);
    }

    @Override
    public int size() {
        return storage.size();
    }
}