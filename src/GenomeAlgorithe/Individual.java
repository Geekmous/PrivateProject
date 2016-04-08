package GenomeAlgorithe;

import java.util.ArrayList;
import java.util.List;

public class Individual {
    // double code
    private List<Double> genomes ;
    
    private double fitness;
    
    Individual() {
        this.genomes = new ArrayList<Double>();
        this.fitness = 0;
    }
    
    public void setGenome(List<Double> genomos) {
        this.genomes = genomos;
    }
    
    public List<Double> getGenome() {
       return this.genomes;
    }
    
    public void  setFitness(double fitness) {
        this.fitness = fitness;
    }
    
    public double getFitness() {
        return this.fitness;
    }
    
    
}
