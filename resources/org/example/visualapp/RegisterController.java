package org.example.visualapp;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterController {
    ArrayList<Label> registers = new ArrayList<>();

    RegisterController(Pane register){
        this.addRegisters(register);
    }

    public void addRegisters(Pane register){
        for (int i = 5; i<9; i++){
            registers.add((Label) register.getChildren().get(i));
        }
    }

    public void replaceRegister(Map<String, Integer> d){
        registers.get(0).setText((d.get("_a") == null)? "*" : Integer.toString(d.get("_a")));
        registers.get(1).setText((d.get("_b") == null)? "*" : Integer.toString(d.get("_b")));
        registers.get(2).setText((d.get("_c") == null)? "*" : Integer.toString(d.get("_c")));
        registers.get(3).setText((d.get("_d") == null)? "*" : Integer.toString(d.get("_d")));
    }
}
