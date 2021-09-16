package com.basejava.storage;

import com.basejava.exception.ExistStorageException;
import com.basejava.exception.NotExistStorageException;
import com.basejava.exception.StorageException;
import com.basejava.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume r1 = new Resume(UUID_1, "name1");
    private static final Resume r2 = new Resume(UUID_2, "name2");
    private static final Resume r3 = new Resume(UUID_3, "name3");
    private static final Resume r4 = new Resume(UUID_4, "name4");

    private final Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(r1);
        storage.save(r3);
        storage.save(r2);
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
    @EnabledIf(value = "value", disabledReason = "saveOverflow() выполняется только для ArrayStorage и SortedArrayStorage")
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

    /* *@Test
    void getAll() {
        Resume[] actualResumes = storage.getAll();
        Resume[] expectedArray = {r1, r2, r3};
        assertArrayEquals(expectedArray, actualResumes);
    }*/

    @Test
    void getAllSorted() {
        List<Resume> actualResumes = storage.getAllSorted();
        List<Resume> expectedResumes = new ArrayList<>();
        expectedResumes.add(r1);
        expectedResumes.add(r2);
        expectedResumes.add(r3);
        assertEquals(expectedResumes, actualResumes);
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }
}