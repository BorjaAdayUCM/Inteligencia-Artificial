package geneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import busqueda.FitnessFunction;
import busqueda.Individual;

public class GeneticAlgorithmNoDestructivo<A> extends GeneticAlgorithm<A> {
	
	public GeneticAlgorithmNoDestructivo(int individualLength, Set<A> finiteAlphabet, double mutationProbability) {
		super(individualLength, finiteAlphabet, mutationProbability);
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
			Individual<A> child = reproduce(x, y);
						
			// if (small random probability) then child <- MUTATE(child)
			if (random.nextDouble() <= this.mutationProbability) {
				child = mutate(child);
			}

			if((fitnessFn.apply(child) > fitnessFn.apply(x)) && (fitnessFn.apply(child) > fitnessFn.apply(y))) newPopulation.add(child);
			else if (fitnessFn.apply(x) > fitnessFn.apply(y)) newPopulation.add(x);
			else newPopulation.add(y);
		}
		notifyProgressTracers(getIterations(), population);
		return newPopulation;
	}
	
	
}
