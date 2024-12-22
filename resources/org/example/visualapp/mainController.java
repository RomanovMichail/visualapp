package org.example.visualapp;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import javax.swing.text.View;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class mainController implements IObserver{
    Model m = BModel.build();
    ICpu cpu = BCpu.build();

    @FXML
    GridPane allView;

    @FXML
    TextField instr;

    @FXML
    Pane register;
    RegisterController registers;

    @FXML
    Pane data;
    MemoryController memory;

    @FXML
    GridPane freqInstr;
    FrequencyController freq;

    @FXML
    void initialize(){
        m.addObserver(this);
        registers = new RegisterController(register);
        memory = new MemoryController(data);
        freq = new FrequencyController(freqInstr);
        Label header = new Label("Частота появления инструкций");
        header.setFont(new Font(13.9));
        freqInstr.add(header, 0, 0, 2, 1);
    }

    @FXML
    void addInstruction(){
        Command c = new Command(instr.getText());
        m.addCommand(c);
    }

    @FXML
    public void resetProgramm(){
        for (Command c: new ArrayList<>(m.allCommand)){
            m.removeCommand(c);
        }
        m.total = 0;
        cpu = BCpu.build();
        registers.replaceRegister(cpu.getData().registers);
        memory.clearMemory();
        freq.clearFreq();
    }

    @Override
    public void event(Model m) {
        allView.getChildren().clear();
        for (Command c: m) {
            instructionController ic = new instructionController();
            FXMLLoader fxmlLoader = new FXMLLoader(appMain.class.getResource("inst_view.fxml"));
            fxmlLoader.setController(ic);
            try {
                Pane pane = fxmlLoader.load();
                ic.setInstruction(c);
                allView.addColumn(0, pane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void performInstruction() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        cpu.exec(m.allCommand.get(m.total));
        freq.addInstr(m.allCommand.get(m.total));
        registers.replaceRegister(cpu.getData().registers);
        memory.replaceData(m.allCommand.get(m.total), cpu.getData().registers);
        if (m.total > 0) {
            Pane prev_instr = (Pane) allView.getChildren().get(m.total - 1);
            for (Node node : prev_instr.getChildren()) {
                if (node instanceof Label label) {
                    label.setStyle("-fx-text-fill: black;");
                }
            }
        }

        Pane now_instr = (Pane) allView.getChildren().get(m.total);
        for (Node node : now_instr.getChildren()) {
            if (node instanceof Label label) {
                label.setStyle("-fx-text-fill: red;");
            }
        }
        m.total += 1;
    }
}
