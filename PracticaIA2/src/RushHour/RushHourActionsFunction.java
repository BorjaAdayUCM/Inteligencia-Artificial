package RushHour;

import java.util.LinkedHashSet;
import java.util.Set;

import RushHour.Vehiculo.Direction;
import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;
import aima.core.search.framework.problem.ActionsFunction;

public class RushHourActionsFunction implements ActionsFunction{

	@Override
	public Set<Action> actions(Object state) {
		RushHourState estado = (RushHourState) state;
		Set<Action> actions = new LinkedHashSet<Action>();
		for(Vehiculo v : estado.getVehiculos()) {
			if(v.getDirection().equals(Direction.HORIZONTAL)) {
				for(int i = 1; i <= estado.getTam() - v.getPosition().getY() - 1; i++) {
					String sAvanzar = "MoveVehicle " + v.getName() + " " + i;
					Action avanzar = new DynamicAction(sAvanzar);
					if(estado.movePossible(avanzar)) actions.add(avanzar);
				}
				for(int i = 1; i <= v.getPosition().getY() - (v.getLength() - 1); i++) {
					String sRetroceder = "MoveVehicle " + v.getName() + " " + i * -1;
					Action retroceder = new DynamicAction(sRetroceder);
					if(estado.movePossible(retroceder)) actions.add(retroceder);
				}
			}
			else {
				for(int i = 1; i <= v.getPosition().getX(); i++) {
					String sAvanzar = "MoveVehicle " + v.getName() + " " + i;
					Action avanzar = new DynamicAction(sAvanzar);
					if(estado.movePossible(avanzar)) actions.add(avanzar);
				}
				for(int i = 1; i < estado.getTam() - v.getPosition().getX() - 1; i++) {
					String sRetroceder = "MoveVehicle " + v.getName() + " " + i * -1;
					Action retroceder = new DynamicAction(sRetroceder);
					if(estado.movePossible(retroceder)) actions.add(retroceder);
				}
			}
		}
		return actions;
	}
}
