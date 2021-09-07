/*ArrayList*/
package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    public void clear() {
        storage.clear();
    }

    protected void saveResume(Resume resume, Object index) {
        storage.add(resume);
    }

    protected void updateResume(Resume resume, Object index) {
        storage.set((Integer) index, resume);
    }

    protected Resume getResume(Object index, Object uuid) {
        return storage.get((Integer) index);
    }

    protected void deleteResume(Object index, Object uuid) {
        storage.remove(((Integer) index).intValue());
    }

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

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }
}