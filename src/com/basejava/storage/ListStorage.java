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

    protected void saveResumeImplementation(Resume resume, int index) {
        storage.add(resume);
    }

    protected void updateResume(Resume resume, int index) {
        storage.set(index, resume);
    }

    protected Resume getResume(int index) {
        return storage.get(index);
    }

    protected void deleteResume(int index) {
        storage.remove(index);
    }

    protected int getIndex(String uuid) {
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
