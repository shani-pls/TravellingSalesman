import java.util.*;
import java.util.stream.Collectors;

// Population class that is comprised of a population, population size and fitness.
public class Population {
	private Individual[] population; 
	private int populationSize;
	private double populationFitness = 0.0;

	public Population(int populationSize, int chromosomeLength, City[] cities) {
		setPopulationSize(populationSize);
		Individual[] individuals = new Individual[populationSize];
		for(int i = 0; i < populationSize; i++) {
			individuals[i] = new Individual(chromosomeLength, cities);
		}
		setPopulation(individuals);
		setPopulationFitness();
	}
	
	public Population(Individual[] population, double fitness) {
		setPopulationSize(population.length);
		setPopulation(population);
		setPopulationFitness(fitness);
	}
	
	public Population(Individual[] population) {
		setPopulationSize(population.length);
		setPopulation(population);
		setPopulationFitness();
	}
	
	public Individual getFittest() {
		Individual fittest = this.population[0];
		for(int i = 0; i < this.populationSize; i++) {
			if(this.population[i].getFitness() > fittest.getFitness()) {
				fittest = this.population[i];
			}
		}
		return fittest;
	}
	
	public Individual getUnfittest() {
		Individual unfittest = this.population[0];
		for(int i = 0; i < this.populationSize; i++) {
			if(this.population[i].getFitness() < unfittest.getFitness()) {
				unfittest = this.population[i];
			}
		}
		return unfittest;
	}

	public Individual[] getPopulation() {
		return population;
	}

	public void setPopulation(Individual[] population) {
		this.population = population;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public double getPopulationFitness() {
		return populationFitness;
	}

	public void setPopulationFitness(double populationFitness) {
		this.populationFitness = populationFitness;
	}
	
	public void setPopulationFitness() {
		double fitness = 0.0;
		for(int i = 0; i < this.populationSize; i++) {
			fitness += this.population[i].getFitness();
		}
		this.populationFitness = fitness/this.populationSize;
	}
	
	public double summedPopulationFitness() {
		double fitness = 0.0;
		for(int i = 0; i < this.populationSize; i++) {
			fitness += this.population[i].getFitness();
		}
		return fitness;
	}
	
	public Population rouletteWheelSelection() {
		int n = this.populationSize;
		Individual[] selectedIndividuals = new Individual[n];
		double summedPopulationFitness = this.summedPopulationFitness();
		int i = 0;
		while(i < n) {
			double pointerPosition = Math.random()*summedPopulationFitness;
			selectedIndividuals[i++] = spinWheel(this.population, n, pointerPosition);
		}
		return new Population(selectedIndividuals);
	}
	
	public Individual spinWheel(Individual[] population, int n, double pointer) {
		double rouletteWheel = 0.0;
		for(Individual i: population) {
			rouletteWheel += i.getFitness();
			if(rouletteWheel >= pointer) {
				return i.copyOf();
			}
		}
		return population[n-1];
	}
	
	public Population tournamentSelection() {
		int n = this.populationSize;
		Individual[] selectedIndividuals = new Individual[n];
		for(int i = 0; i < n; i++) {
			int randIndex1 = (int) Math.round(Math.random()*(n-1));
			int randIndex2 = (int) Math.round(Math.random()*(n-1));
			selectedIndividuals[i] = this.population[randIndex1].getFitness() > this.population[randIndex2].getFitness()? this.population[randIndex1] : this.population[randIndex2];
		}
		return new Population(selectedIndividuals);
	}
	
	/**
	 * Prints the solution.
	 *
	 * @param newPopulation the new population
	 */
	public Population sortByFitness() {
		List<Individual> finalIndividuals = new ArrayList<Individual>();
		for(Individual k: this.getPopulation()) {
			finalIndividuals.add(k);
		}
		Comparator<Individual> binaryComparator = (i1, i2) -> ((Double)i2.getFitness()).compareTo((Double)i1.getFitness());
		List<Individual> sorted = 
			      finalIndividuals.stream().sorted(binaryComparator).collect(Collectors.toList());
		Individual[] sortedIndividuals = new Individual[this.getPopulationSize()];
		int i = 0;
		for(Individual l:sorted) {
			sortedIndividuals[i++] = l;
		}
		return new Population(sortedIndividuals);
	}
	
	/**
	 * Prints the solution.
	 *
	 * @param newPopulation the new population
	 */
	public Population sortByFitnessReverse() {
		List<Individual> finalIndividuals = new ArrayList<Individual>();
		for(Individual k: this.getPopulation()) {
			finalIndividuals.add(k);
		}
		Comparator<Individual> binaryComparator = (i1, i2) -> ((Double)i1.getFitness()).compareTo((Double)i2.getFitness());
		List<Individual> sorted = 
			      finalIndividuals.stream().sorted(binaryComparator).collect(Collectors.toList());
		Individual[] sortedIndividuals = new Individual[this.getPopulationSize()];
		int i = 0;
		for(Individual l:sorted) {
			sortedIndividuals[i++] = l;
		}
		return new Population(sortedIndividuals);
	}
	
	public Population elitistReplacement(Population newPopulation, double generationGap) {
		int n = this.populationSize;
		Individual[] replacedIndividuals = new Individual[n];
		newPopulation = newPopulation.sortByFitness();//fittest to least fit
		Population currentPopulation = this.sortByFitnessReverse(); //least fit to fittest
		int mappingPoint = (int) Math.round(n*generationGap);
		for(int i = 0; i < mappingPoint; i++) {
			replacedIndividuals[i] = newPopulation.getPopulation()[i];
		}
		for(int i = mappingPoint; i < n; i++) {
			replacedIndividuals[i] = currentPopulation.getPopulation()[i];
		}
		return new Population(replacedIndividuals);
	}
	
	public Population copyOf() {
		int n = this.populationSize;
		Individual[] individualCopy = new Individual[n];
		for(int i = 0; i < n; i++) {
			individualCopy[i] = this.population[i].copyOf();
		}
		return new Population(individualCopy, this.populationFitness);
	}
	
	public String toString() {
		String result = "";
		int n = this.populationSize;
		for(int i = 0; i < n-1; i++) {
			result += "" + this.population[i] + "\n";
		}
		result += "" + this.population[n-1];
		return result;
	}
}
