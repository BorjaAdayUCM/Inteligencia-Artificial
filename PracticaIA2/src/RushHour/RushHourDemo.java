package RushHour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import RushHour.Vehiculo.Direction;
import aima.core.agent.Action;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.UniformCostSearch;

public class RushHourDemo {

	/*
	 * Para usar un tablero personalizado se debe dar por par�metro de la siguiente forma:
	 * 
	 * Tamaño del tablero: int
	 * 
	 * Lista de vehñculos
	 * nombre de vehiculo: char
	 * fila: int
	 * columna: int
	 * tamaño del vehiculo: int
	 * Direcion del vehiculo: char 'v' para vertical o 'h' para horizontal.
	 * 
	 * Ejemplo1: 6, a 0 0 2 v, r 2 1 2 h, b 3 1 2 h, c 5 1 2 h, d 0 3 3 h, e 1 3 2 h, f 2 2 2 v, g 2 3 2 v, h 4 3 2 v, i 0 4 3 v, j 4 5 2 h
	 * Ejemplo2: 8, r 3 1 2 h, a 3 2 3 v, b 6 3 2 h, c 2 3 2 v, d 3 4 3 v, e 6 6 3 h, f 2 5 2 v, g 0 6 4 v
	 * Ejemplo3: 6, a 0 2 3 h, b 1 0 2 v, c 3 1 2 h, d 1 2 2 h, r 2 2 2 h, e 3 2 2 v, f 0 3 2 v, g 0 4 2 v, h 0 5 2 v, i 2 3 2 v, j 4 4 2 h, k 3 5 2 h, l 4 5 2 v
	 * Ejemplo4: 6, r 2 1 2 h, a 3 1 2 h, b 4 1 2 v, c 0 2 2 v, d 2 2 2 v, e 4 3 2 h, f 5 3 2 h, g 0 5 3 h, h 1 3 2 v, i 3 4 2 h, j 2 5 3 v
	 *
	 * Recuerda:
	 *           - Siempre debe haber un coche llamado 'r' en la fila = (tam / 2 - 1) en direccion horizontal.
	 *           - No deben haber dos coches con el mismo nombre.
	 */
	public static void main(String[] args) {
		RushHourState tablero;
		if(args.length > 0) {
			int tamTablero = Integer.parseInt(args[0].substring(0, args[0].length() - 1));
			ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
			for(int i = 1; i < args.length; i+= 5)
			{
				char nombreVehiculo = args[i].charAt(0);
				int x = Integer.parseInt(args[i + 1]);
				int y = Integer.parseInt(args[i + 2]);
				int tam = Integer.parseInt(args[i + 3]);
				Vehiculo.Direction dir = (args[i + 4].charAt(0) == 'v' ? Direction.VERTICAL : Direction.HORIZONTAL);
				Vehiculo v = new Vehiculo(x, y, tam, dir, nombreVehiculo);
				listaVehiculos.add(v);
			}
			tablero = new RushHourState(tamTablero, listaVehiculos);
		}
		else tablero =  new RushHourState();
		
		//Descomenta esta funcion si quieres ejecutar todos los algoritmos sobre el tablero dado y comenta la siguiente para no mezclar resultados.
		//RushHourAlgoritmos(tablero);
		
		//Dentro de esta funcion debes aportarle el algoritmo que deseas usar, por defecto viene A* con GraphSearch y la heuristica obstaculos
		RushHour(tablero);
	}

	private static void RushHour(RushHourState tablero) {
		System.out.println("RushHourDemo-->\n");
		try {
			Problem problem = new Problem(tablero, RushHourFunctionFactory.getActionsFunction(), RushHourFunctionFactory.getResultFunction(), new RushHourGoalTest());
			AStarSearch search = new AStarSearch(new GraphSearch(), new ObstaculosHeuristic());
			SearchAgent agent = new SearchAgent(problem, search);
			System.out.println("Tablero inicial: " + '\n' + tablero.toString() + '\n');
			for(Action a: agent.getActions()) {
				System.out.println(a);
				tablero.move(a);
			}
			System.out.println(agent.getInstrumentation());
			System.out.println("\nTablero final: " + '\n' + tablero.toString() + '\n');
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void RushHourAlgoritmos(RushHourState tablero) {
		System.out.println("RushHourDemo-->\n");
		try {
			Problem problem = new Problem(tablero, RushHourFunctionFactory.getActionsFunction(), RushHourFunctionFactory.getResultFunction(), new RushHourGoalTest());

			AStarSearch search = new AStarSearch(new GraphSearch(), new ObstaculosHeuristic());
			SearchAgent agent = new SearchAgent(problem, search);

			AStarSearch search1 = new AStarSearch(new GraphSearch(), new DistanciaMetaHeuristic());
			SearchAgent agent1 = new SearchAgent(problem, search1);

			BreadthFirstSearch search2 = new BreadthFirstSearch();
			SearchAgent agent2 = new SearchAgent(problem, search2);

			GreedyBestFirstSearch search3 = new GreedyBestFirstSearch(new GraphSearch(), new ObstaculosHeuristic());
			SearchAgent agent3 = new SearchAgent(problem, search3);

			GreedyBestFirstSearch search4 = new GreedyBestFirstSearch(new GraphSearch(), new DistanciaMetaHeuristic());
			SearchAgent agent4 = new SearchAgent(problem, search4);

			DepthFirstSearch search5 = new DepthFirstSearch(new GraphSearch());
			SearchAgent agent5 = new SearchAgent(problem, search5);

			UniformCostSearch search6 = new UniformCostSearch();
			SearchAgent agent6 = new SearchAgent(problem, search6);

			Map<String, SearchAgent> mapaAgentes = new HashMap<String, SearchAgent>();
			mapaAgentes.put("A* GRAPH OBSTACULOS", agent);
			mapaAgentes.put("A* GRAPH DISTANCIA META", agent1);
			mapaAgentes.put("ANCHURA SEARCH", agent2);
			mapaAgentes.put("GreedyBestFirstSearch GRAPH OBSTACULOS", agent3);
			mapaAgentes.put("GreedyBestFirstSearch GRAPH DISTANCIA META", agent4);
			mapaAgentes.put("PROFUNDIDAD GRAPH", agent5);
			mapaAgentes.put("COSTE UNIFORME", agent6);
			
			Iterator<Entry<String, SearchAgent>> iti = mapaAgentes.entrySet().iterator();
			while(iti.hasNext()) {
				Entry<String, SearchAgent> entry = iti.next();
				System.out.println(entry.getKey());
				for(Action a: entry.getValue().getActions()) System.out.println(a);
				System.out.println(entry.getValue().getInstrumentation().toString() + '\n');
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}


