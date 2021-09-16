package com.basejava.storage;

import com.basejava.exception.StorageException;
import com.basejava.model.Resume;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    void saveOverflow() {
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("name"));
            }
        } catch (StorageException ex) {
            fail("Преждевременное переполнение хранилища");
        }
        assertThrows(StorageException.class, () -> storage.save(new Resume("name")));
    }

}