package GenomeAlgorithe;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Engine {
   
    public  static void main(String[] args) {
       /* try {
            PrintStream ps = new PrintStream(new FileOutputStream("./output.txt"));
            System.setOut(ps);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } */
        double leftPoint = -1;
        double rightPoint = 2;
        int maxGeneration = 5000;
        int populationSize = 50;
        double mutationRate = 0.8;
        double maxPerturbation = 0.001;
        double crossoverRate = 0.0;
        int genomoLength = 1;
        
        Population population = new Population(populationSize, mutationRate, crossoverRate, genomoLength, leftPoint, rightPoint, new mathFunction());
        population.initPopulation(maxPerturbation);
        population.calculateBestWorstAverageTotal();
        while(population.generation < maxGeneration) {          
            population.mutate();
            population.epoch();
            population.calculateBestWorstAverageTotal();
            System.out.println("The generation:" + population.generation + " \n");
            population.disPlay();
            population.generation++;
        }
    }
}
