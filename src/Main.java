import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Enter in the following order:\nnumber of cities,\npopulation size,\nnumber of generations,\ncrossover probability,\nmutation probability");
		Scanner sc  = new Scanner(System.in);
		int numberOfCities = sc.nextInt();
		int populationSize = sc.nextInt();
		int generations = sc.nextInt();
		double crossoverProbability = sc.nextDouble();
		double mutationProbability = sc.nextDouble();
//		String selectionMethod = sc.next();
//		String replacementMethod = sc.next();
//		double generationGap = 0.0;
//		if(replacementMethod.equals("elitist"))
//			generationGap = sc.nextDouble();
		sc.close();
		
		//Initialize cities
		City[] cities = new City[numberOfCities];
		for(int i = 0; i < numberOfCities; i++) {
			cities[i] = new City();
		}
		
		//Initialize population
		Population initialPopulation = new Population(populationSize, numberOfCities, cities);
		
		//Simulation with RWS and full replacement
		//Initialize GA
			GA ga = new GA(generations, crossoverProbability, mutationProbability);
			Population finalPopulationRouletteFull = ga.solve(initialPopulation, cities, "roulette", "full", 1.0);
			
			int[][] table = prepareTable(ga.getBestRouteGenerations());
			printPathsToFile(table, "paths_roulette_full");
			printFitnessToFile(ga.getBestRouteFitnessGenerations(), "fitness_roulette_full");
			printCitiesToFile(cities, "cities");

			System.out.println("Fittest: " +  String. format("%.4f", finalPopulationRouletteFull.getFittest().getFitness()));
			System.out.println("Least distance: " +  String. format("%.4f", (numberOfCities*numberOfCities)/finalPopulationRouletteFull.getFittest().getFitness()));
			
		//Simulation with TS and full replacement
			GA ga2 = new GA(generations, crossoverProbability, mutationProbability);
			Population finalPopulationTSFull = ga2.solve(initialPopulation, cities, "tournament", "full", 1.0);
			
			int[][] table2 = prepareTable(ga2.getBestRouteGenerations());
			printPathsToFile(table2, "paths_tournament_full");
			printFitnessToFile(ga2.getBestRouteFitnessGenerations(), "fitness_tournament_full");

			System.out.println("Fittest: " +  String. format("%.4f", finalPopulationTSFull.getFittest().getFitness()));
			System.out.println("Least distance: " +  String. format("%.4f", (numberOfCities*numberOfCities)/finalPopulationTSFull.getFittest().getFitness()));
			
		//Simulation with RWS and elitist replacement
			GA ga3 = new GA(generations, crossoverProbability, mutationProbability);
			Population finalPopulationRWSElistist = ga3.solve(initialPopulation, cities, "roulette", "elitist", 0.2);
			
			int[][] table3 = prepareTable(ga3.getBestRouteGenerations());
			printPathsToFile(table3, "paths_roulette_elitist");
			printFitnessToFile(ga3.getBestRouteFitnessGenerations(), "fitness_roulette_elitist");

			System.out.println("Fittest: " +  String. format("%.4f", finalPopulationRWSElistist.getFittest().getFitness()));
			System.out.println("Least distance: " +  String. format("%.4f", (numberOfCities*numberOfCities)/finalPopulationRWSElistist.getFittest().getFitness()));
			
		//Simulation with TS and elitist replacement
			GA ga4 = new GA(generations, crossoverProbability, mutationProbability);
			Population finalPopulationTSElistist = ga4.solve(initialPopulation, cities, "tournament", "elitist", 0.2);
			
			int[][] table4 = prepareTable(ga4.getBestRouteGenerations());
			printPathsToFile(table4, "paths_tournament_elitist");
			printFitnessToFile(ga4.getBestRouteFitnessGenerations(), "fitness_tournament_elitist");

			System.out.println("Fittest: " +  String. format("%.4f", finalPopulationTSElistist.getFittest().getFitness()));
			System.out.println("Least distance: " +  String. format("%.4f", (numberOfCities*numberOfCities)/finalPopulationTSElistist.getFittest().getFitness()));
//		
	}
	
	public static int[][] prepareTable(List<int[]> solutions){
		int rows = solutions.size();
		int cols = solutions.get(0).length;
		int[][] table = new int[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				table[i][j] = solutions.get(i)[j];
			}
		}
		return table;
	}
	
	public static void printPathsToFile(int[][] paths, String filename) {
		try
		{
			System.out.println("printing paths");
			FileWriter writer = new FileWriter(filename + ".csv");
			for(int i = 0; i < paths.length; i++) {
				for(int j = 0; j < paths[0].length-1; j++) {
					writer.write("" + paths[i][j] + ",");
				}
				writer.write(paths[i][paths[0].length-1] + "\n");
			}
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		    System.out.println("No such file exists.");
		}
	}
	
	public static void printFitnessToFile(double[][] fitnessValues, String filename) {
		try
		{
			int numGenerations = fitnessValues.length;
			System.out.println("printing fitness values");
			FileWriter writer = new FileWriter(filename + ".csv");
			for(int i = 0; i < numGenerations; i++) {
				writer.write(String.format("%.4f", fitnessValues[i][0]) + "," + String.format("%.4f", fitnessValues[i][1]) + "," + String.format("%.4f", fitnessValues[i][2]) + "\n");
			}
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		    System.out.println("No such file exists.");
		}
	}
	
	public static void printCitiesToFile(City[] cities, String filename) {
		try
		{
			int numberOfCities = cities.length;
			System.out.println("printing cities");
			FileWriter writer = new FileWriter(filename + ".csv");
			for(int i = 0; i < numberOfCities; i++) {
				writer.write("" + cities[i].getxCoordinate() + "," + cities[i].getyCoordinate() + "\n");
			}
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		    System.out.println("No such file exists.");
		}
	}

	
}
