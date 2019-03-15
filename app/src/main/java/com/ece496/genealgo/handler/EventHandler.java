package com.ece496.genealgo.handler;

import java.time.LocalDateTime;
import java.util.Vector;

import com.ece496.database.Event;

public class EventHandler{
    public Vector<Event> events;

    public EventHandler(){
        this.events = new Vector<Event>();
    }
    public EventHandler add_event(String name){
        events.addElement(new Event(name));
        return this;
    }
    public EventHandler add_event(String name, LocalDateTime deadline){
        events.addElement(new Event(name, deadline));
        return this;
    }
}