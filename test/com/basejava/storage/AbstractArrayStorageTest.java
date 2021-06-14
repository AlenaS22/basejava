package com.basejava.storage;

import com.basejava.exception.ExistStorageException;
import com.basejava.exception.NotExistStorageException;
import com.basejava.exception.StorageException;
import com.basejava.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AbstractArrayStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_5 = "uuid5";

    private static final Resume r1 = new Resume(UUID_1);
    private static final Resume r2 = new Resume(UUID_2);
    private static final Resume r3 = new Resume(UUID_3);
    private static final Resume r4 = new Resume(UUID_4);
    private static final Resume r5 = new Resume(UUID_5);

    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    void save() {
        storage.save(new Resume(UUID_4));
        Assertions.assertEquals(4, storage.size());
        Assertions.assertEquals(r4, storage.get(UUID_4));
    }

    @Test
    void update() {
        storage.update(r3);
        Assertions.assertEquals(r3, storage.get(UUID_3));
    }

    @Test
    void get() {
        Assertions.assertEquals(r2, storage.get(UUID_2));
    }

    @Test
    void delete() {
        storage.delete(UUID_3);
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_3));
        Assertions.assertEquals(2, storage.size());
    }

    @Test
    void getAll() {
        Resume[] array = storage.getAll();
        Assertions.assertEquals(r1, array[0]);
        Assertions.assertEquals(r2, array[1]);
        Assertions.assertEquals(r3, array[2]);
        Assertions.assertEquals(3, array.length);
    }

    @Test
    void size() {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(r1));
    }

    @Test
    void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(r5));
    }

    @Test
    void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_4));
    }

    @Test
    void saveOverflow() {
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException ex) {
            Assertions.fail("Test failed");
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }
}