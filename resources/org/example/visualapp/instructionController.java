package org.example.visualapp;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.*;

public class instructionController {
    @FXML
    Label func;
    @FXML
    Label var;
    @FXML
    Label val1;
    @FXML
    Label val2;

    Command c;

    static LinkedHashMap<instructionController, Command> dict = new LinkedHashMap<>();

    Model m = BModel.build();

    public void remove_repeat(){
        LinkedHashMap<instructionController, Command> map = new LinkedHashMap<>();
        int count = 0;
        for (instructionController key: dict.keySet()){
            if (count >= dict.size()-m.allCommand.size()){
                map.put(key, dict.get(key));
            }
            count++;
        }
        dict = map;
    }

    public void setInstruction(Command c){
        this.c = c;
        func.setText(c._func);
        dict.put(this, c);
        remove_repeat();
        ArrayList<Label> labels = new ArrayList<>(){{
            add(var); add(val1); add(val2);
        }};
        for (int i = 0; i < 3; i++){
            if (i < c._var.length){
                labels.get(i).setText(c._var[i]);}
            else{
                labels.get(i).setText("");
            }
        }
    }

    public void removeInstr(){
        m.removeCommand(c);
        dict.remove(this);
    }

    public void move_right(){
        m.moveRight(c);
        ArrayList<instructionController> keys = new ArrayList<>(dict.keySet());
        int index = keys.indexOf(this);
        if (index!=dict.size()-1) {
            Command cur = this.c;
            this.setInstruction(keys.get(index + 1).c);
            keys.get(index + 1).setInstruction(cur);
        }
        else {
            ArrayList<Command> values = new ArrayList<>(dict.values());
            values.remove(this.c); values.add(0, this.c);
            for (int i = 0; i<dict.size(); i++){
                keys.get(i).setInstruction(values.get(i));
            }
        }
    }

    public void move_left(){
        m.moveLeft(c);
        ArrayList<instructionController> keys = new ArrayList<>(dict.keySet());
        int index = keys.indexOf(this);
        if (index!=0) {
            Command cur = this.c;
            this.setInstruction(keys.get(index - 1).c);
            keys.get(index - 1).setInstruction(cur);
        }
        else {
            ArrayList<Command> values = new ArrayList<>(dict.values());
            values.remove(this.c); values.add(this.c);
            for (int i = 0; i<dict.size(); i++){
                keys.get(i).setInstruction(values.get(i));
            }
        }
    }
}
