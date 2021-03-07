package geneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import busqueda.FitnessFunction;
import busqueda.Individual;

public class GeneticAlgorithmProbabilidadCruce<A> extends GeneticAlgorithm<A> {
	
	private double probabilidadCruce;
	
	public GeneticAlgorithmProbabilidadCruce(int individualLength, Set<A> finiteAlphabet, double mutationProbability, double probabilidadCruce) {
		super(individualLength, finiteAlphabet, mutationProbability);
		this.probabilidadCruce = probabilidadCruce;
	}

	@Override
	protected List<Individual<A>> nextGeneration(List<Individual<A>> population, FitnessFunction<A> fitnessFn) {
		
		List<Individual<A>> newPopulation = new ArrayList<Individual<A>>(population.size());
		

		// for i = 1 to SIZE(population) do
		for (int i = 0; i < population.size(); i++) {
			
			// x <- RANDOM-SELECTION(population, FITNESS-FN)
			Individual<A> x = randomSelection(population, fitnessFn);
			
			// y <- RANDOM-SELECTION(population, FITNESS-FN)
			Individual<A> y = randomSelection(population, fitnessFn);
			
			// child <- REPRODUCE(x, y)
			Individual<A> child = null;
			if(random.nextDouble() <= this.probabilidadCruce) {
				child = reproduce(x, y);
			}
			else {
				if(random.nextInt(2) == 0) {
					child =  x;
				}
				else {
					child = y;
				}
			}
			
			// if (small random probability) then child <- MUTATE(child)
			if (random.nextDouble() <= this.mutationProbability) {
				child = mutate(child);
			}
			
			// add child to new_population
			newPopulation.add(child);
		}
		notifyProgressTracers(getIterations(), population);
		return newPopulation;
	}
	
	
}
