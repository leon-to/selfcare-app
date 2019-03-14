package GeneticAlgorithm.object;

import java.time.LocalDateTime;

public class Event{
    public String name;
    public LocalDateTime deadline;

    
    public Event(String name, LocalDateTime deadline){
        this.name = name;
        this.deadline = deadline;
    }
    public Event(String name){
        this(name, LocalDateTime.now().plusDays(7));
    }
    public Event(){
        this("no name", LocalDateTime.now().plusDays(7));
    }

    @Override
    public String toString(){
        return "Event: " + name + ", Deadline: " + deadline;
    }
}