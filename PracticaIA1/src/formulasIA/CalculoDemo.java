package formulasIA;

import java.util.HashSet;
import java.util.Set;

import busqueda.Individual;
import geneticAlgorithm.GeneticAlgorithm;
import geneticAlgorithm.GeneticAlgorithm2Hijos;
import geneticAlgorithm.GeneticAlgorithmNoDestructivo;
import geneticAlgorithm.GeneticAlgorithmProbabilidadCruce;

@SuppressWarnings("unused")
public class CalculoDemo {
	private static final int _size = 6;

	public static void main(String[] args) 
	{
		calculoGeneticAlgorithmSearch(Integer.parseInt(args[0]));
	}

	public static void calculoGeneticAlgorithmSearch(int valorObjetivo) {
		//System.out.println("\nCalculoDemo GeneticAlgorithm  -->");
		try {
			CalculoFitnessFunction fitnessFunction = new CalculoFitnessFunction(valorObjetivo);
			// Generate an initial population
			Set<Individual<Elemento>> population = new HashSet<Individual<Elemento>>();
			for (int i = 0; i < 50; i++) {
				population.add(fitnessFunction.generateRandomIndividual());
			}
			GeneticAlgorithm<Elemento> ga = new GeneticAlgorithmNoDestructivo<Elemento>(_size, fitnessFunction.getFiniteAlphabetForBoardOfSize(), 0.99);

			// Run for a set amount of time
			Individual<Elemento> bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, fitnessFunction, 1000L);
			System.out.println("Max Time (1 second) Best Individual=\n"+ fitnessFunction.getRepresentacionForIndividual(bestIndividual));
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + fitnessFunction.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");

			// Run till goal is achieved
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction,fitnessFunction, 0L);
			System.out.println("");
			System.out.println("Goal Test Best Individual=\n"+ fitnessFunction.getRepresentacionForIndividual(bestIndividual));
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + fitnessFunction.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");

		} 

		catch (Exception e) {
			e.printStackTrace();
		}

	}
}

