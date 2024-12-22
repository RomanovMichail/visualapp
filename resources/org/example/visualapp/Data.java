package org.example.visualapp;

import java.util.HashMap;
import java.util.Map;

public class Data{

    Map<String, Integer> registers = new HashMap<>();
    Map<String, Integer> cells = new HashMap<>();
    protected int[] _array;
    Data(){
        registers.put("_a", null); cells.put("_a", null);
        registers.put("_b", null); cells.put("_b", null);
        registers.put("_c", null); cells.put("_c", null);
        _array = new int[1024];
    }

    public void init(String index, String value){
        int i = Integer.parseInt(index);
        if (i>=0 && i<1024){
            _array[i] = Integer.parseInt(value);
        }
    }

    public void add(String val1, String val2, String var){
        registers.put(var, (registers.get(val1))+registers.get(val2));
    }

    public void sub(String val1, String val2, String var) {
        registers.put(var, registers.get(val1) - registers.get(val2));
    }

    public void multi(String val1, String val2, String var) {
        registers.put(var, registers.get(val1) * registers.get(val2));
    }


    public void ld(String reg, String index){
        if (!cells.containsValue(Integer.parseInt(index)) || cells.get(reg) == Integer.parseInt(index)){
            registers.put(reg, _array[Integer.parseInt(index)]);
            cells.put(reg, Integer.parseInt(index));
        }
    }

    public void st( String index, String reg){
        _array[Integer.parseInt(index)] = registers.get(reg);
    }

    public void mv(String reg1, String reg2){
        registers.put(reg1, registers.get(reg2));
    }

    public void div(String val1, String val2, String var) {
        registers.put(var, registers.get(val1)/registers.get(val2));
    }

    public void print() {
        for (String reg : registers.keySet()) {
            System.out.println("Регистр: " + reg + " = " + registers.get(reg));
        }
    }
}
