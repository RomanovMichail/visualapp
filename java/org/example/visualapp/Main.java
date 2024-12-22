package org.example.visualapp;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        Command[] prog = { new Command("init", "11", "25"),
                new Command("init", "12", "5"),
                new Command("ld", "_a", "10"),
                new Command("ld", "_b", "11"),
                new Command("ld", "_c", "15"),
                new Command("add", "_d", "_a", "_b"),
                new Command("print")};

        ICpu cpu = BCpu.build();
        Executor exec = new Executor(cpu);
        exec.run(prog);




    }
}