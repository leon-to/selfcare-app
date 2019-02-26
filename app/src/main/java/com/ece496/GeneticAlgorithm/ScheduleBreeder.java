package GeneticAlgorithm;

import java.util.Random;

public class ScheduleBreeder{
    public Schedule breed(Schedule male, Schedule female){
        Schedule offspring = null;
        // offspring = crossover(male, female);
        // offspring = mutation(offspring);
        return offspring;
    }

    // private Schedule crossover(Schedule male, Schedule female){
    //     int gene_length = male.chromosome.length;
    //     Random random = new Random();
    //     int crossover_point = random.nextInt(gene_length);
    //     Gene[] genes = new Gene[gene_length];
        
    //     for(int i=0; i<gene_length; i++){
    //         if (i < crossover_point){
    //             genes[i] = male.chromosome.genes[i].clone();
    //         }else{
    //             genes[i] = female.chromosome.genes[i].clone();
    //         }
    //     }   
    //     // Schedule offspring = new Schedule(new Chromosome(genes, gene_length));
    //     Schedule offspring = null;
    //     return offspring;
    // }

    private Schedule mutation(Schedule offspring){

        return offspring;
    }

    
}