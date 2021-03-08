package RushHour;

import aima.core.search.framework.evalfunc.HeuristicFunction;

public class ObstaculosHeuristic implements HeuristicFunction {

	//Devuelve la cantidad de vehiculos distintos entre el coche rojo y el objetivo.
	@Override
	public double h(Object state) {
		int ret=0;
		RushHourState estado = (RushHourState) state;
		Vehiculo temp, prev = null;
		int x = estado.searchVehiculo('r').getPosition().getX(); int i = estado.searchVehiculo('r').getPosition().getY() + 1;
		for (;i<estado.getTam();i++)
		{
			temp = estado.getBoard()[x][i];
			if((temp != null)&&(temp != prev))
			{
				ret++;
				temp = prev;
			}
		}
		return ret;
	}

}
