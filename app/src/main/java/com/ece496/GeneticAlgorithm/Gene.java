package GeneticAlgorithm;

import java.time.LocalDateTime;

public class Gene{
    // attached to an event (feature)
    final Event event;
    // time slot (feature appearance) 
    LocalDateTime timeslot;

    public Gene(Event event){
        this.event = event;
    }
    public Gene(Event event, LocalDateTime timeslot){
        this.event = event;
        this.timeslot = timeslot;
    }

    public LocalDateTime get_timeslot(){
        return timeslot;
    }
    public void set_timeslot(LocalDateTime timeslot){
        this.timeslot = timeslot;
    }
    public Gene clone(){
        return new Gene(event, timeslot);
    }
}