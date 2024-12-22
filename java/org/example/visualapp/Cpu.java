package org.example.visualapp;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Cpu implements ICpu {
    public Data _data;
    Cpu(Data data){
        _data = data;
    }
    public Data getData() {
        return _data;}
    public void exec(Command elem) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?>[] parameter = new Class[elem._var.length];
        for (int i = 0; i < elem._var.length; i++) {
            parameter[i] = String.class;
        }
        Method method = _data.getClass().getMethod(elem._func, parameter);
        method.invoke(_data, (Object[]) elem._var);
    }
}
