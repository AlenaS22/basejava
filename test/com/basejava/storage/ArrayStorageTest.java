package com.basejava.storage;

class ArrayStorageTest extends AbstractStorageTest {
    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    public boolean value() {
        return true;
    }
}