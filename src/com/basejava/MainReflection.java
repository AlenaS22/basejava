// TODO lesson4 В MainReflection вызовите у Resume, через отражение, метод toString. Выведите результат на консоль
package com.basejava;

import com.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "new_uuid");
        System.out.println(resume);
        // toString() reflection
        Method toStringMethod = resume.getClass().getDeclaredMethod("toString");
        System.out.println(toStringMethod.invoke(resume));

    }
}
