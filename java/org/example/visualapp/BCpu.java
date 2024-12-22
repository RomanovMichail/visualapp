package org.example.visualapp;

public class BCpu {
    static ICpu build(){
        Data data = new Data();
        return new Cpu(data);
    }
}
