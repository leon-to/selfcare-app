package GeneticAlgorithm;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

import java.lang.Exception;
// Schedule = A human
public class Schedule implements Comparable<Schedule>{ 
    int[] chromosome;
    double fitness;

    public Schedule(int[] chromosome){
        this.chromosome = chromosome;
    }
    @Override
    public String toString(){
        return "C: " + Arrays.toString(chromosome) + " F: " + fitness;
    }
    @Override
    public int compareTo(Schedule schedule){
        double delta = this.fitness - schedule.fitness;
        if (delta < 0){
            return 1;
        }else if (delta > 0){
            return -1;
        }else{
            return 0;
        }
    }
}