package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void saveResume(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Object searchKey) { //
        return storage.get(searchKey.toString());
    }

    @Override
    protected void deleteResume(Object searchKey) { //
        storage.remove(searchKey.toString());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Object searchKey = -1;
        for (Map.Entry<String, Resume> i : storage.entrySet()) {
            if (uuid.equals(i.getKey())) {
                searchKey = uuid;
                break;
            }
        }
        return searchKey;
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
