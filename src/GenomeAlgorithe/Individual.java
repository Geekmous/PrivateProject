package GenomeAlgorithe;

import java.util.ArrayList;
import java.util.List;

public class Individual {
    // double code
    private List<Double> genomos ;
    
    private double fitness;
    
    Individual() {
        this.genomos = new ArrayList<Double>();
        this.fitness = 0;
    }
    
    public void setGenome(List<Double> genomos) {
        this.genomos = genomos;
    }
    
    public List<Double> getGenome() {
       return this.genomos;
    }
    
    public void  setFitness(double fitness) {
        this.fitness = fitness;
    }
    
    public double getFitness() {
        return this.fitness;
    }
    
    
}
