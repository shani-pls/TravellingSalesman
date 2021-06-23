import java.util.ArrayList;
import java.util.List;

// Genetic Algorithm class that iterates through generations of a population
public class GA {
	private int generations;
	private double crossoverProbability;
	private double mutationProbability;
	private List<int[]> bestRouteGenerations = new ArrayList<int[]>();
	private double[][] bestRouteFitnessGenerations;
	
	public GA(int generations, double Pc, double Pm) {
		setGenerations(generations);
		setCrossoverProbability(Pc);
		setMutationProbability(Pm);
		bestRouteFitnessGenerations = new double[generations][3];
	}
	
	public Population solve(Population initialPopulation, City[] cities, String selectionMethod, String replacementMethod, double generationGap) {
		Population currentPopulation = initialPopulation.copyOf();
		Population initialCurrentPopulation = initialPopulation.copyOf();
		int counter = 1;
		while(counter <= generations) {

			//Selection
			if(selectionMethod.equals("roulette"))
				currentPopulation = currentPopulation.rouletteWheelSelection();
			else
				currentPopulation = currentPopulation.tournamentSelection();
			
			//Crossover
			currentPopulation = crossover(currentPopulation, this.crossoverProbability, cities);
			
			//Mutation
			currentPopulation = mutation(currentPopulation, this.mutationProbability, cities);
			
			//Replacement
			//Full replacement. Therefore new population is current population
			if(replacementMethod.equals("elitist")) {
				currentPopulation = initialCurrentPopulation.elitistReplacement(currentPopulation, generationGap);
			}
			initialCurrentPopulation = currentPopulation;
			
			//Update values

			bestRouteGenerations.add(currentPopulation.getFittest().getChromosome());
			bestRouteFitnessGenerations[counter-1][0] = currentPopulation.getUnfittest().getFitness();			
			bestRouteFitnessGenerations[counter-1][1] = currentPopulation.getPopulationFitness();
			bestRouteFitnessGenerations[counter-1][2] = currentPopulation.getFittest().getFitness();
			counter++;
		}
		return currentPopulation;
	}
	
	public static Population crossover(Population population, double Pc, City[] cities) {
		int n = population.getPopulationSize();
		Individual[] crossedOverIndividuals = new Individual[n];
		Individual[] tempInput = new Individual[2]; Individual[] tempOutput = new Individual[2];
		for(int i = 0; i < n-1; i+=2) {
			if(Math.random() < Pc) {
			 tempInput[0] = population.getPopulation()[i]; tempInput[1] = population.getPopulation()[i+1];
			 tempOutput = Individual.orderedCrossover(tempInput, cities);
			 crossedOverIndividuals[i] = tempOutput[0];crossedOverIndividuals[i+1] = tempOutput[1];
			} else {
				crossedOverIndividuals[i] = population.getPopulation()[i];
				crossedOverIndividuals[i+1] = population.getPopulation()[i+1];
			}
		}
		return new Population(crossedOverIndividuals);
	}
	
	public static Population mutation(Population population, double Pm, City[] cities) {
		int n = population.getPopulationSize();
		Individual[] mutatedIndividuals = new Individual[n];
		for(int i = 0; i < n; i++) {
			mutatedIndividuals[i] = population.getPopulation()[i].swapMutation(Pm, cities);
		}
		return new Population(mutatedIndividuals);
	}
	
	public int getGenerations() {
		return generations;
	}
	
	public void setGenerations(int generations) {
		this.generations = generations;
	}

	public double getCrossoverProbability() {
		return crossoverProbability;
	}
	
	public void setCrossoverProbability(double crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}
	
	public double getMutationProbability() {
		return mutationProbability;
	}
	
	public void setMutationProbability(double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	public List<int[]> getBestRouteGenerations() {
		return bestRouteGenerations;
	}

	public void setBestRouteGenerations(List<int[]> bestRouteGenerations) {
		this.bestRouteGenerations = bestRouteGenerations;
	}

	public double[][] getBestRouteFitnessGenerations() {
		return bestRouteFitnessGenerations;
	}

	public void setBestRouteFitnessGenerations(double[][] bestRouteFitnessGenerations) {
		this.bestRouteFitnessGenerations = bestRouteFitnessGenerations;
	}
}
