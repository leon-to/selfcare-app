package com.ece496.database;

import com.orm.SugarRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Event  extends SugarRecord{
    String name;

    LocalDateTime t_start;
    LocalDateTime t_end;
    LocalDateTime t_deadline;

    int frequency;
    LocalDateTime t_freq_start;
    LocalDateTime t_freq_end;

    public Event(String name,
                 LocalDateTime t_start, LocalDateTime t_end, LocalDateTime t_deadline,
                 int frequency
    ){
        this.name = name;

        this.t_start = t_start;
        this.t_end = t_end;
        this.t_deadline = t_deadline;

        this.frequency = frequency;
    }

    public Event(String name){
        this(name, null, null, null, Frequency.NONE);
    }
    public Event(String name, LocalDateTime t_deadline){
        this(name, null, null, t_deadline, Frequency.NONE);
    }
    public Event(){
        this("no name", LocalDateTime.now().plusDays(7));
    }


    public String get_name(){
        return get_name();
    }
    public  LocalDateTime get_deadline(){
        return t_deadline;
    }


    public String toString(){
        return "Event: " + name + ", Deadline: " + t_deadline;
    }
}