package org.example.visualapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Model implements Iterable<Command> {
    ArrayList<Command> allCommand = new ArrayList<>();
    ArrayList<IObserver> allObserver = new ArrayList<>();
    int total = 0;

    void eventCall(){
        allObserver.forEach(action->action.event(this));
    }

    void addCommand(Command c){
        allCommand.add(c);
        eventCall();
    }

    public void removeCommand(Command c){
        allCommand.remove(c);
        eventCall();
    }

    public void moveRight(Command c){
        int index = allCommand.indexOf(c);
        if (index != allCommand.size()-1) {
            Collections.swap(allCommand, index, index + 1);
        }
        else {
            allCommand.remove(index); allCommand.add(0, c);
        }
    }

    public void moveLeft(Command c){
        int index = allCommand.indexOf(c);
        if (index != 0) {
            Collections.swap(allCommand, index, index - 1);
        }
        else {
            allCommand.remove(0); allCommand.add(c);
        }
    }

    public void addObserver(IObserver e){
        allObserver.add(e);
        eventCall();
    }


    @Override
    public Iterator<Command> iterator() {
        return allCommand.iterator();
    }
}
