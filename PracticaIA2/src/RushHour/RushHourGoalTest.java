package RushHour;

import aima.core.search.framework.problem.GoalTest;

public class RushHourGoalTest implements GoalTest {

    public boolean isGoalState(Object state) {
      RushHourState estado = (RushHourState) state;
      return estado.searchVehiculo('r').getPosition().getX() == estado.getTam() / 2 - 1 && estado.searchVehiculo('r').getPosition().getY() == estado.getTam() - 1;
    }
  }
