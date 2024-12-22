package org.example.visualapp;



import java.util.*;
import java.util.stream.Collectors;


public class Programm implements Iterable<Command> {
    ArrayList<Command> commands = new ArrayList<>();
    HashMap<String, Integer> dict = new HashMap<>();

    @Override
    public Iterator<Command> iterator() {
        return commands.iterator();
    }

    public void add(Command command){
        commands.add(command);
        if (dict.get(command._func) == null){
            dict.put(command._func, 1);
        }
        else{
            dict.put(command._func, dict.get(command._func)+1);
        }
    }

    public void add(ArrayList<Command> list){
        for (Command c: list) {this.add(c);}
    }

    public void remove(Command c){
        commands.remove(c);
        dict.put(c._func, dict.get(c._func)-1 == 0? null : dict.get(c._func)-1);
    }

    public String most_instruction(){
        return dict.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null).getKey();

    }

    public Map<String, Integer> sort_dict_inst(){
        return dict.entrySet()
                .stream()
                .filter((pair) -> pair.getValue() != null)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public List<String> range_address(){
        List<String> memory = commands
                .stream()
                .filter((command) -> List.of("init", "ld", "st").contains(command._func))
                .map((command) -> command._func.equals("ld") ? command._var[1] : command._var[0])
                .distinct()
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .toList();
        return List.of(memory.get(0), memory.get(memory.size()-1));
    }
}