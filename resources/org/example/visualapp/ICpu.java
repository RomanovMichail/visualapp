package org.example.visualapp;

import java.lang.reflect.InvocationTargetException;

public interface ICpu {
    Data getData();
    public void exec(Command c) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
