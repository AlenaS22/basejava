package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new LinkedHashMap<>();

    public void clear() {
        storage.clear();
    }

    protected void saveResume(Resume resume, int index) {
        storage.put(resume.getUuid(), resume);
    }

    protected void updateResume(Resume resume, int index) {
        storage.replace(resume.getUuid(), resume);
    }

    protected Resume getResume(int index, String uuid) {
        return storage.get(uuid);
    }

    protected void deleteResume(int index, String uuid) {
        storage.remove(uuid);
    }

    protected int getIndex(String uuid) {
        int index = -1;
        for (Map.Entry<String, Resume> i : storage.entrySet()) {
            if (uuid.equals(i.getKey())) {
                index = uuid.hashCode();
                break;
            }
        }
        return index;
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    public int size() {
        return storage.size();
    }

}