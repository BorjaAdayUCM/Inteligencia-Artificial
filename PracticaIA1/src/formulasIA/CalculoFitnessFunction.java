package formulasIA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import busqueda.FitnessFunction;
import busqueda.GoalTest;
import busqueda.Individual;

public class CalculoFitnessFunction implements FitnessFunction<Elemento>, GoalTest
{
	private final CalculoGoalTest goalTest = new CalculoGoalTest();
	static int[] posiblesValores = {1,2,3,4,5,6,7,8,9,10,25,50};
	private int valorObjetivo;
	
	public CalculoFitnessFunction(int valorObjetivo) 
	{
		super();
		this.valorObjetivo = valorObjetivo;
	}

	// START - Interface FitnessFunction
	@Override
	public double apply(busqueda.Individual<Elemento> individual) {
		Representacion board = getRepresentacionForIndividual(individual);
		int valor = board.obtenerValor();
		int diferencia = Math.abs(valorObjetivo - valor);
		return Integer.MAX_VALUE /( diferencia + 1);
	}
	
	// START - Interface GoalTest
	@SuppressWarnings("unchecked")
	public boolean isGoalState(Object state) {
		return goalTest.isGoalState(getRepresentacionForIndividual((Individual <Elemento>) state));
	}

	// END - Interface GoalTest

	public Representacion getRepresentacionForIndividual(Individual <Elemento> individual) {//NUMERO OBJETIVO, el primer elemento tiene operacion
		Representacion board = new Representacion(this.valorObjetivo);
		for (int i = 0; i< individual.length(); i++)
		{
			board.insertarElemento(i,individual.getRepresentation().get(i));
		}
		return board;
	}
	
	public Individual<Elemento> generateRandomIndividual()
	{
		List<Elemento> individualRepresentation = new ArrayList<Elemento>();
		for (int i = 0; i < 6; i++) {
			individualRepresentation.add(new Elemento(posiblesValores[new Random().nextInt(posiblesValores.length)], new Random().nextInt(4)));
		}
		Individual<Elemento> individual = new Individual<Elemento> (individualRepresentation);
		return individual;
	}

	public Set<Elemento> getFiniteAlphabetForBoardOfSize()
	{
		Set<Elemento> fab = new HashSet<Elemento>();
		for (int i = 0; i< posiblesValores.length; i++) 
			for (int j = 0; j < 4; j++)
				fab.add(new Elemento(posiblesValores[i],j));
		return fab;
	}
}
