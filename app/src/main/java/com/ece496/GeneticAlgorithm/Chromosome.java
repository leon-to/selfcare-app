package GeneticAlgorithm;

public class Chromosome{
    Gene[] genes;
    int length;

    public Chromosome (Gene[] genes, int length){
        this.genes = genes;
        this.length = length;
    }
}