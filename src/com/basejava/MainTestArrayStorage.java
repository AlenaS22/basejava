package com.basejava;

import com.basejava.model.Resume;
import com.basejava.storage.ArrayStorage;
import com.basejava.storage.Storage;

/**
 * Test for your com.basejava.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid2", "name2");
       // r1.setUuid("uuid1");
        final Resume r2 = new Resume("uuid1", "name2");
       // r2.setUuid("uuid2");
        final Resume r3 = new Resume("uuid3", "name3");
       // r3.setUuid("uuid3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        printAll();

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

     //   System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        //update
        ARRAY_STORAGE.update(r2);
        printAll();

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
