package GeneticAlgorithm.handler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Logger;

import GeneticAlgorithm.object.Timeslot;

public class TimeslotHandler{
    private static final Logger logger = Logger.getGlobal();

    public Vector<Timeslot> timeslots;

    public TimeslotHandler(){
        this.timeslots = new Vector<Timeslot>();
    }
    public TimeslotHandler add_timeslot(int year, int month, int day, int hour){
        timeslots.addElement(new Timeslot(year, month, day, hour));
        return this;
    }
    // public TimeslotHandler sort_timeslots(){
    //     Arrays.sort(timeslots);
    //     start_time = timeslots[0];
    //     end_time = timeslots[timeslots.length-1];
    //     logger.info("Timeslots after sort: " + Arrays.toString(timeslots));
    //     return this;
    // }

    // public double compute_timescore(int timeslot_id, LocalDateTime due_time){
    //     if(due_time == null)
    //         return 1;

    //     Duration total_time = Duration.between(start_time, due_time);
    //     Duration time_left_before_due_time = Duration.between(timeslots[timeslot_id], due_time);
        
    //     double time_score = time_left_before_due_time.toHours() / time_left_before_due_time.toHours();
    //     logger.info("Timescore: " + time_score);
    //     return time_score;
    // }
}