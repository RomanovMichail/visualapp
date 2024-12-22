package org.example.visualapp;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Map;

public class MemoryController {
    ArrayList<Label> memory = new ArrayList<>();

    MemoryController(Pane data){
        this.addMemory(data);
    }

    public void addMemory(Pane data){
        for (int i = 0; i < 50; i++) {
            memory.add((Label) data.getChildren().get(i));
        }
    }

    public void replaceData(Command c, Map<String, Integer> d){
        if ("init".equals(c._func) || "st".equals(c._func)){
            Label label = memory.get(Integer.parseInt(c._var[0]));
            if ("init".equals(c._func)){
                label.setText(c._var[0] + ":" + c._var[1]);
                label.setStyle((Integer.parseInt(c._var[1])!=0)? "-fx-text-fill: red;" : "-fx-text-fill: black;");
            }
            else {
                label.setText(c._var[0] + ":" + String.valueOf(d.get(c._var[1])));
                label.setStyle((d.get(c._var[1]) != 0) ? "-fx-text-fill: red;" : "-fx-text-fill: black;");
            }
        }
    }

    public void clearMemory(){
        for (int i = 1; i<memory.size(); i++){
            memory.get(i).setStyle("-fx-text-fill: black;");
            memory.get(i).setText(String.valueOf(i)+ ":0");
        }
    }
}
