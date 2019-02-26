package GeneticAlgorithm;

public class Event{
    String title;
    String description;
    String location;

    public Event(String title){
        this.title = title;
    }
    @Override
    public String toString(){
        return "Title: " + title;
    }
}