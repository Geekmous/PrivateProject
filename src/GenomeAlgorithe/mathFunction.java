package GenomeAlgorithe;

import java.util.List;

public class mathFunction extends AbstractFitnessFunction {
    private final static double  PI = 3.1415;
    public double getFitness(List genomos) {
        double fitness = 0;
        for(double genomo :(List<Double>) genomos) {
            //f(x) = xsin(10πx) +2.0
            fitness += genomo * Math.sin(10 * PI * genomo) + 2.0;
        }       
        return fitness;
    }
    
    
}
