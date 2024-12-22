package org.example.visualapp;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.List;
import java.util.Map;

public class FrequencyController{
    Programm prog;
    GridPane grid;
    int size;

    public FrequencyController(GridPane grid){
        prog = new Programm();
        this.grid = grid;
        size = 1;
    }

    public void addInstr(Command c){
        prog.add(c);
        this.fillGrid();
    }

    Map<String, Integer> getFreq(){
        return prog.sort_dict_inst();
    }

    public void clearFreq(){
        this.removeRow();
        prog.commands.clear();
        prog.dict.clear();
    }

    public void removeRow(){
        List<Node> nodes = grid.getChildren()
                .stream()
                .filter((node)-> GridPane.getRowIndex(node)>0)
                .toList();
        grid.getChildren().removeAll(nodes);
    }

    public void fillGrid(){
        this.removeRow();
        Map<String, Integer> map = this.getFreq();
        for (String ic: map.keySet()){
            Label instr = new Label(ic); instr.setFont(new Font(13));
            GridPane.setMargin(instr, new Insets(5, 0, 0, 50));
            Label value = new Label(map.get(ic).toString()); value.setFont(new Font(13));
            GridPane.setMargin(value, new Insets(5, 0, 0, 50));
            grid.add(instr, 0, size);
            grid.add(value, 1, size);
            size++;
        }
    }
}
