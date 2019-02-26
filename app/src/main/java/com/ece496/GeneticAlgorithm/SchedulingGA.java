package GeneticAlgorithm;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.*;

public class SchedulingGA{
    private static final Random random = new Random();
    private static final Logger logger = Logger.getGlobal();
    
    public void ga(){
        // Schedule[] population = initialize_population (size);
        // evaluate_fitness(population);
        // best_individual = get_best_individual();
    }
    public static void main(String[] args) {
        int population_size = 10;
        int chromosome_length = 3;
        int number_of_gens = 10;
        int k = 2; 
        double p_mutation = 0.1;
        double fitness_tolerance = 0.0001;
        double prev_max_fitness = Double.MAX_VALUE;
        Schedule[] population = initialize_population(population_size, chromosome_length, number_of_gens);
        evaluate_scheduling_fitness(population);

        do{
            /* SELECTION */
            Schedule parent_1 = tournament_selection(population, k/*tournament size*/);
            Schedule parent_2 = tournament_selection(population, k);
            while (parent_1==parent_2)
                parent_2 = tournament_selection(population, k);
            /* CROSSOVER */
            Schedule child_1 = crossover(parent_1, parent_2);
            Schedule child_2 = crossover(parent_2, parent_1);
            /* MUTATION */
            mutation(child_1, chromosome_length, number_of_gens, p_mutation);
            mutation(child_2, chromosome_length, number_of_gens, p_mutation);
            /* EVALUATE FITNESS*/
            evaluate_scheduling_fitness(child_1);
            evaluate_scheduling_fitness(child_2);
            /* REPLACE */
            replace (population, child_1, child_2);

        }while(compute_fitness_change(population) > fitness_tolerance);
        // evaluate
    }
    
    private static void replace(Schedule[] population, Schedule child_1, Schedule child_2) {
    }

    public static Schedule[] initialize_population(int population_size, int chromosome_length, int number_of_gens) {
        // create population
        Schedule[] population = new Schedule[population_size];
        for(int i=0; i<population_size; i++){
            // create chromosome
            int[] chromosome = new int[chromosome_length];
            for(int j=0; j<chromosome_length; j++){
                chromosome[j] = random.nextInt(number_of_gens);
            }
            population[i] = new Schedule(chromosome);
            population[i].fitness = random.nextDouble();
        }

        logger.info("Random initialize " + population_size + " schedules:\n" + Arrays.toString(population));
        return population;
    }
    public static Schedule tournament_selection(Schedule[] population, int k){
        int i, j; 
        i = random.nextInt(population.length);
        j = random.nextInt(population.length);
        while(i == j)
            j = random.nextInt(population.length);
        return population[i].fitness > population[j].fitness ? population[i]:population[j]; 
    }
    public static Schedule crossover(Schedule parent_1, Schedule parent_2){
        int chromosome_length = parent_1.chromosome.length;
        int crossover_point = random.nextInt(chromosome_length);
        Schedule child = new Schedule(new int[chromosome_length]);

        for(int i=0;i<chromosome_length; i++){
            child.chromosome[i] = (i<crossover_point) ? parent_1.chromosome[i] : parent_2.chromosome[i];
        }
        logger.info("Parents: " + parent_1 + parent_2 + " Child: " + child);
        return child;
    }
    
    public static void mutation(Schedule child, int chromosome_length, int number_of_gens, double p_mutation){
        if (random.nextDouble() > p_mutation)
            return;

        int mutation_gene = random.nextInt(chromosome_length);
        child.chromosome[mutation_gene] = random.nextInt(number_of_gens);
        
        logger.info("Child after mutation: " + child);
    }
    public static void evaluate_scheduling_fitness(Schedule individual, int chromosome_length, int number_of_gens){
        int total_fitness = 0;
        int[] chromosome = individual.chromosome;
        int max_score_per_gene = 1;

        for(int i=0; i<chromosome_length; i++){
            // if events don't overlap, increase by 1
            boolean is_overlapped = false;
            for (int j=0; j<chromosome_length; j++){
                if (i!=j && chromosome[i]/*current event's timeslot*/ == chromosome[j]/*other events' timeslot*/)
                    is_overlapped = true;
            }
            if (!is_overlapped) total_fitness++; 

        }

        individual.fitness = total_fitness / (chromosome_length * max_score_per_gene);
        logger.info("Fitness: " + individual);
    }
    public static void evaluate_scheduling_fitness(Schedule[] population, int chromosome_length, int number_of_gens){
        for(Schedule individual : population){
            evaluate_scheduling_fitness(individual, chromosome_length, number_of_gens);
        }
    }
    private static double compute_fitness_change(Schedule[] population) {
        return 0.000001;
    }
}