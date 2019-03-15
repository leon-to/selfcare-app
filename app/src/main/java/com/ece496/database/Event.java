package com.ece496.database;

import com.orm.SugarRecord;

import java.time.LocalDateTime;

public class Event  extends SugarRecord<Event>  {
    String name;
    LocalDateTime deadline;

    
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

    public String get_name(){
        return get_name();
    }
    public  LocalDateTime get_deadline(){
        return deadline;
    }

    @Override
    public String toString(){
        return "Event: " + name + ", Deadline: " + deadline;
    }
}