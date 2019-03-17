package com.ece496.backend;

import com.ece496.database.Event;

/**
 * This class take an event object and add to 
 */
public class Scheduler{
    public void schedule(Event event){
        event.save();
    }
}
