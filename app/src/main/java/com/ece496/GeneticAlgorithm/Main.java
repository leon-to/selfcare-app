package GeneticAlgorithm;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.logging.*;


public class Main{
    static final Logger logger = Logger.getGlobal();

    public static void main(String[] args) throws Exception {
        ScheduleBreeder breeder = new ScheduleBreeder();
        Schedule male;
        Event[] events = new Event[3];
        Event event_1= new Event("Write 1000 word essay");
        Event event_2= new Event("Workout");
        events[0] = event_1;
        events[1] = event_1;
        events[2] = event_2;

        LocalDateTime [] timeslots = new LocalDateTime[10];
        timeslots[0] = LocalDateTime.of(2019, 3, 1, 9, 0);
        timeslots[1] = LocalDateTime.of(2019, 3, 1, 10, 0);
        timeslots[2] = LocalDateTime.of(2019, 3, 1, 11, 0);
        timeslots[3] = LocalDateTime.of(2019, 3, 1, 12, 0);
        timeslots[4] = LocalDateTime.of(2019, 3, 1, 14, 0);
        timeslots[5] = LocalDateTime.of(2019, 3, 1, 16, 0);
        timeslots[6] = LocalDateTime.of(2019, 3, 2, 8, 0);
        timeslots[7] = LocalDateTime.of(2019, 3, 2, 10, 0);
        timeslots[8] = LocalDateTime.of(2019, 3, 2, 11, 0);
        timeslots[9] = LocalDateTime.of(2019, 3, 2, 12, 0);
        
        logger.setLevel(Level.FINE);

        int population_size = 10;
        int chromosome_length = events.length;
        int gene_type = timeslots.length;

        double p_crossover = 1;
        double p_mutation = 0.1;

        double fitness_tolerance = 0.0001;

        TimeslotHandler timeslot_handler = new TimeslotHandler(timeslots);

        SchedulePopulation schedule_population = new SchedulePopulation(
            timeslot_handler,
            events, timeslots, 
            population_size, chromosome_length, gene_type,
            p_crossover, p_mutation,
            fitness_tolerance
        );

        boolean children_only = true;     

        timeslot_handler.sort_timeslots();
        timeslot_handler.compute_timescore(2, timeslots[9]);
        timeslot_handler.compute_timescore(3, timeslots[9]);

        schedule_population .initialize()
                            .fitness(!children_only);

        // while(true)
        do{
            schedule_population .tournament_selection()
                                .onepoint_crossover()
                                .onepoint_mutation()
                                .fitness(children_only)
                                .replace();
        }
        while(schedule_population.termination());
        
    }
}