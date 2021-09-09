package com.basejava.storage;

import com.basejava.exception.ExistStorageException;
import com.basejava.exception.NotExistStorageException;
import com.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    public void save(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());
        if (searchKey.hashCode() >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveResume(resume, searchKey);
    }

    protected abstract void saveResume(Resume resume, Object searchKey); ///

    protected abstract Object getSearchKey(String uuid); ///

    public void update(Resume resume) {
        Object searchKey = verifyKeyNotExist(resume.getUuid());
        updateResume(resume, searchKey);
    }

    protected abstract void updateResume(Resume resume, Object searchKey); ///

    protected Object verifyKeyNotExist(String uuid) {  ///
        Object searchKey = getSearchKey(uuid);
        if (searchKey.hashCode() < 0) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public Resume get(String uuid) {
        Object searchKey = verifyKeyNotExist(uuid);
        return getResume(searchKey);
    }

    protected abstract Resume getResume(Object searchKey); //

    public void delete(String uuid) {
        Object searchKey = verifyKeyNotExist(uuid);
        deleteResume(searchKey);
    }

    protected abstract void deleteResume(Object searchKey); //
}