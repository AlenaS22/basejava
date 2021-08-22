package com.basejava.storage;

import com.basejava.exception.ExistStorageException;
import com.basejava.exception.NotExistStorageException;
import com.basejava.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume r1 = new Resume(UUID_1);
    private static final Resume r2 = new Resume(UUID_2);
    private static final Resume r3 = new Resume(UUID_3);
    private static final Resume r4 = new Resume(UUID_4);

    private final Storage storage = new ListStorage();

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
        assertEquals(0, storage.size());
    }

    @Test
    void save() {
        storage.save(r4);
        assertEquals(4, storage.size());
        assertEquals(r4, storage.get(UUID_4));
    }

    @Test
    void saveExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(r1));
    }

    @Test
    void update() {
        storage.update(r3);
        assertEquals(r3, storage.get(UUID_3));
    }

    @Test
    void updateNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.update(r4));
    }

    @Test
    void get() {
        assertEquals(r2, storage.get(UUID_2));
    }

    @Test
    void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_4));
    }

    @Test
    void delete() {
        storage.delete(UUID_3);
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_3));
        assertEquals(2, storage.size());
    }

    @Test
    void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_4));
    }

    @Test
    void getAll() {
        Resume[] array = storage.getAll();
        assertEquals(r1, array[0]);
        assertEquals(r2, array[1]);
        assertEquals(r3, array[2]);
        assertEquals(3, array.length);

        Resume[] expectedArray = {r1, r2, r3};
        assertArrayEquals(expectedArray, array);
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }
}
