package RushHour;

import aima.core.search.framework.evalfunc.HeuristicFunction;


public class DistanciaMetaHeuristic implements HeuristicFunction {
	
	//Devuelve la distancia desde el coche rojo a la meta.
	@Override
	public double h(Object state) {
		RushHourState estado = (RushHourState) state;
		return estado.getTam() - estado.searchVehiculo('r').getPosition().getY() - 1;		
	}
}
