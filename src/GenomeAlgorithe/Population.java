package GenomeAlgorithe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {
    //个体的列表
    List<Individual> individuals;
    //种群数量
    int populationSize;
    //染色体的长度
    int chromosomeLength;
    //种群总体的适应度
    double totalFitness;
    //种群最好的适应度
    double bestFitness;
    //种群平均的适应度
    double averageFitness;
    //种群最低的适应度
    double worstFitness;
    //适应度最高的个体
    Individual fittestIndividual;
    //适应度最低的个体
    Individual worstIndividual;
    //基因交叉的概率
    double crossverRate;
    //基因变异的概率
    double mutationRate;
    //代数计数器
    int generation;   
    //最大变异步长
    double maxPerturbation;
    //适应性函数
    AbstractFitnessFunction fitnessFunction;
    //区间左端点
    double leftPoint;
    //区间右端点
    double rightPoint;
    
    Population(int populationSize, double mutationRate, double crossRate, int genomeLength, double leftPoint, double rightPoint, AbstractFitnessFunction fitnessFunction) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossverRate = crossRate;
        this.chromosomeLength = genomeLength;
        this.leftPoint = leftPoint;
        this.rightPoint = rightPoint;
        this.fitnessFunction = fitnessFunction;
        this.totalFitness = 0;
        this.averageFitness = 0;
        this.bestFitness = 0;
        this.worstFitness = 999999;        
    }
    
    public void initPopulation(double maxPerturbation) {
            
        this.maxPerturbation = maxPerturbation;
        this.individuals = new ArrayList<Individual>();
        //初始化种群
        for(int i = 0 ; i < this.populationSize; i++) {
            Individual individual = new Individual();
            List<Double> genomo = individual.getGenome();
            for(int j = 0; j < this.chromosomeLength; j++){          
                genomo.add(new Random().nextDouble() * (this.rightPoint - this.leftPoint) + this.leftPoint);
            }
           //应用适应性函数 
            double fitness = this.fitnessFunction.getFitness(individual.getGenome());
            individual.setFitness(fitness);
            this.individuals.add(individual);
        }
        this.generation = 1;
    }
    //计算种群最好最坏平均的适应度
    public void calculateBestWorstAverageTotal() {
        this.totalFitness = 0;
        for(Individual individual:individuals) {
            double fitness = individual.getFitness();
            this.totalFitness +=fitness;
            if(fitness > this.bestFitness) {
                this.bestFitness = fitness;
                this.fittestIndividual = individual;
            }
            
            if(fitness < this.worstFitness) {
                this.worstFitness = fitness;
                this.worstIndividual = individual;
            }
            
            this.averageFitness = this.totalFitness/this.populationSize;
        }
    }
    //赌轮盘的实现
    public Individual getChromosomeRoulette() {
        double slice = (new Random().nextDouble() * this.totalFitness);
        
        Individual chosenIndividual = null;
        
        double fitnessCount = 0;
        int number = 0;
        for(Individual individual:this.individuals) {
            number++;
            fitnessCount += individual.getFitness();
          //  System.out.println("the fitnessCount "+ number+":" + fitnessCount + "and the slice is "+ slice +"\n");
            if(fitnessCount > slice) {
                
                chosenIndividual = individual;
                break;
            }
        }
        if(number == 50) {
            chosenIndividual = this.individuals.get(number - 1);
        }
     //   System.out.println("the ChosenIndividual's Fitness :" +chosenIndividual.getFitness() + " and the genomo is " + chosenIndividual.getGenome().get(0) + "\n");
        return chosenIndividual;
    }
    //基因变异
    public void mutate() {
        for(Individual individual : this.individuals) {
            if(new Random().nextDouble() < this.mutationRate) {     
                for(int i = 0; i < individual.getGenome().size(); i++) {                   
                    double change = (new Random().nextDouble() - 0.5d) * this.maxPerturbation;       
                    double genomo = individual.getGenome().remove(i);
                   // System.out.println("the genomo is :" + genomo + "   and the change is : " + change);
                   genomo += change;                  
                   if(genomo < this.leftPoint) {
                       genomo = this.rightPoint;
                   }     
                   if(genomo > this.rightPoint) {
                       genomo = this.leftPoint;
                   }
                 //  System.out.println("the genomo after change is :" + genomo +"\n");
                   individual.getGenome().add(i, genomo);
               }
            }          
            //应用适应性函数
            double fitness = this.fitnessFunction.getFitness(individual.getGenome());
            individual.setFitness(fitness);
                   
        }
    }
    //生成新的种群
    public void epoch() {
       // Individual theChosenOne = this.getChromosomeRoulette();    
        List<Double> genomos = this.fittestIndividual.getGenome();      
        List<Double> copy = new ArrayList<Double>();      
        for(Double genomo : genomos) {
            copy.add(genomo);
        }       
        this.worstIndividual.setGenome(copy);       
        this.worstIndividual.setFitness(this.fittestIndividual.getFitness());
        this.worstFitness = this.bestFitness;
        
    }
    //结果显示
    public void disPlay() {
        System.out.println("The bestFitness is :" + this.bestFitness + "\n最佳适应度基因取值 "+ this.fittestIndividual.getGenome().get(0) + "\n The worstFitness is :" + this.worstFitness + "\n The AverageFitness is :" + this.averageFitness +"\n-----------------------------------\n");
     /*   for(Individual individual : this.individuals) {
            List genomo = individual.getGenome();
            System.out.println("one individual's genomo is :" + genomo.get(0) +  " and the fitness :" + individual.getFitness() + "\n");          
        } */
      //  System.out.println("----------------------------");
    }
}
