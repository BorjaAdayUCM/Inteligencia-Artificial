package geneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import busqueda.FitnessFunction;
import busqueda.Individual;

public class GeneticAlgorithm2Hijos<A> extends GeneticAlgorithm<A> {
	
	public GeneticAlgorithm2Hijos(int individualLength, Set<A> finiteAlphabet, double mutationProbability) {
		super(individualLength, finiteAlphabet, mutationProbability);
	}

	@Override
	protected List<Individual<A>> nextGeneration(List<Individual<A>> population, FitnessFunction<A> fitnessFn) {
		
		List<Individual<A>> newPopulation = new ArrayList<Individual<A>>(population.size());
		

		// for i = 1 to SIZE(population) do
		for (int i = 0; i < population.size() / 2; i++) {
			// x <- RANDOM-SELECTION(population, FITNESS-FN)
						Individual<A> x = randomSelection(population, fitnessFn);
						
						// y <- RANDOM-SELECTION(population, FITNESS-FN)
						Individual<A> y = randomSelection(population, fitnessFn);
						
						// child <- REPRODUCE(x, y)
						Individual<A> child1 = reproduce(x, y);
						Individual<A> child2 = reproduce(x, y);
						
						// if (small random probability) then child <- MUTATE(child)
						if (random.nextDouble() <= this.mutationProbability) {
							child1 = mutate(child1);
						}
						
						if (random.nextDouble() <= this.mutationProbability) {
							child2 = mutate(child2);
						}
						// add child to new_population
						newPopulation.add(child1);
						newPopulation.add(child2);
		}
		notifyProgressTracers(getIterations(), population);
		return newPopulation;
	}
	
	
}
