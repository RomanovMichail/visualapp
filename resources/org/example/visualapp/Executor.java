package org.example.visualapp;

import java.lang.reflect.InvocationTargetException;

public class Executor {
    ICpu _cpu;
    Executor(ICpu cpu){
        _cpu = cpu;
    }
    public void run(Command [] commands)  throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Command elem : commands) {
            _cpu.exec(elem);
        }
    }

    public void run(Programm commands)  throws NoSuchMethodException, InvocationTargetException, IllegalAccessException{
        for (Command elem : commands){
            _cpu.exec(elem);
        }
    }
}
