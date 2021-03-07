package RushHour;

import aima.core.agent.Action;
import aima.core.search.framework.problem.ResultFunction;

public class RushHourResultFunction implements ResultFunction{

	@Override
	public Object result(Object state, Action a) {
		if (a == null) return state;
		RushHourState estado = (RushHourState) state;
		RushHourState nuevoEstado = new RushHourState(estado);
		nuevoEstado.move(a);
		return nuevoEstado;
	}

}
