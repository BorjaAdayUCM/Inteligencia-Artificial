package formulasIA;

import busqueda.GoalTest;

public class CalculoGoalTest implements GoalTest {

	public boolean isGoalState(Object state) 
	{
		Representacion estado = (Representacion) state;
		return estado.obtenerValor() == estado.getNumObjetivo();
	}
}