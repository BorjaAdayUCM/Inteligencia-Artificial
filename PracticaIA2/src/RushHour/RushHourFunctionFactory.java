package RushHour;


import aima.core.search.framework.problem.ActionsFunction;
import aima.core.search.framework.problem.ResultFunction;

public class RushHourFunctionFactory {
	private static ActionsFunction _actionsFunction = null; 
	private static ResultFunction _resultFunction = null;
    
	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new RushHourActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new RushHourResultFunction();
		}
		return _resultFunction;
	}
}
