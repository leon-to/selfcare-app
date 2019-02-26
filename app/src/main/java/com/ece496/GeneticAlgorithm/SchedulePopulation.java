package GeneticAlgorithm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.logging.*;


public class SchedulePopulation{
    private static final Logger logger = Logger.getGlobal();
    public static final Random random = new Random();
    
    public final TimeslotHandler timeslot_handler;

    Event[] events;
    LocalDateTime[] timeslots;

    int population_size;
    int chromosome_length;
    int gene_type;

    double p_crossover;
    double p_mutation;

    double fitness_tolerance;
    
    // private
    Schedule[] population = null;
    Schedule best_schedule;
    Schedule[] parents = new Schedule[2];
    Schedule[] children = new Schedule[2];

    public SchedulePopulation(  TimeslotHandler timeslot_handler, 
                                Event[] events, LocalDateTime[] timeslots, 
                                int population_size,int chromosome_length, int gene_type,
                                double p_crossover, double p_mutation,
                                double fitness_tolerance)
    {
        this.timeslot_handler = timeslot_handler;    
        this.events = events;
        this.timeslots = timeslots;

        this.population_size = population_size;
        this.chromosome_length = chromosome_length;
        this.gene_type = gene_type;

        this.p_crossover = p_crossover;
        this.p_mutation = p_mutation;

        this.fitness_tolerance = fitness_tolerance;
    }
    
    public SchedulePopulation initialize(){
        // create population
        population = new Schedule[population_size];
        for(int i=0; i<population_size; i++){
            // create chromosome
            int[] chromosome = new int[chromosome_length];
            for(int j=0; j<chromosome_length; j++){
                chromosome[j] = random.nextInt(gene_type);
            }
            population[i] = new Schedule(chromosome);
            population[i].fitness = random.nextDouble();
        }

        logger.info("Random initialize " + population_size + " schedules:\n" + Arrays.toString(population));
        return this;
    }
    private Schedule _tournament_selection(){
        int i, j; 
        i = random.nextInt(population.length);
        j = random.nextInt(population.length);
        while(i == j)
            j = random.nextInt(population.length);
        return population[i].fitness > population[j].fitness ? population[i]:population[j]; 
    }
    public SchedulePopulation tournament_selection(){
        parents[0] = _tournament_selection();
        parents[1] = _tournament_selection();
        while(parents[0] == parents[1])
            parents[1] = _tournament_selection();
            
        logger.info("Selected Parents:\n" + Arrays.toString(this.parents));
        return this;
    }
    private Schedule _onepoint_crossover(){
        if (random.nextDouble() > p_crossover)
            return parents[0];

        int crossover_point = random.nextInt(chromosome_length);
        Schedule child = new Schedule(new int[chromosome_length]);

        for(int i=0;i<chromosome_length; i++){
            child.chromosome[i] = (i<crossover_point) ? parents[0].chromosome[i] : parents[1].chromosome[i];
        }
        return child;
    }
    public SchedulePopulation onepoint_crossover(){
        children[0] = _onepoint_crossover();
        children[1] = _onepoint_crossover();
        logger.info("After crossover, Child 1: " + children[0] + " Child 2: " + children[1]);
        return this;
    }
    public void _onepoint_mutation(Schedule child){
        if (random.nextDouble() > p_mutation)
            return;

        int mutation_gene = random.nextInt(chromosome_length);
        child.chromosome[mutation_gene] = random.nextInt(gene_type);
    }
    public SchedulePopulation onepoint_mutation(){
        _onepoint_mutation(children[0]);
        _onepoint_mutation(children[1]);
        logger.info("After mutation, Child 1: " + children[0] + " Child 2: " + children[1]);
        return this;
    }
    public SchedulePopulation fitness(boolean evaluate_child){
        Schedule[] population;
        if (evaluate_child){
            population = this.children;
        }else
            population = this.population;

        for(Schedule schedule : population){
            int total_fitness = 0;
            int[] chromosome = schedule.chromosome;
            int event_max_score = 1;

            for(int i=0; i<chromosome_length; i++){
                // if events don't overlap, increase by 1
                boolean is_overlapped = false;
                for (int j=0; j<chromosome_length; j++){
                    if (i!=j && chromosome[i]/*current event's timeslot*/ == chromosome[j]/*other events' timeslot*/)
                        is_overlapped = true;
                }
                if (!is_overlapped) total_fitness++; 

            }

            schedule.fitness = total_fitness / (events.length * event_max_score);
        }

        logger.info("Fitness: " + Arrays.toString(population));
        return this;
    }
    public boolean termination(){
        return false;
    }

	public void replace() {
	}
}