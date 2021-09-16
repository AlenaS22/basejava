package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapStorageResume extends AbstractStorage {
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
        return (Resume) searchKey;
    }

    @Override
    protected void deleteResume(Object searchKey) { //
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.containsKey(uuid) ? storage.get(uuid) : -1;
    }

    /* *@Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }*/

    @Override
    public List<Resume> getCopyStorage() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
