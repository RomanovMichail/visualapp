package org.example.visualapp;

import java.util.Arrays;

public class Command {
    public String _func; String[] _var = new String[0];
    public Command(String func, String ... var) {
        if (var.length == 0) {
            String[] parts = func.split(" ");
            if (parts.length > 1) {
                _var = Arrays.copyOfRange(parts, 1, parts.length);
            }
            _func = parts[0];
        }
        else {
            _func = func;
            _var = var.clone();
        }
    }
}
