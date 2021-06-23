import java.util.Arrays;

public class Individual {

	private int[] chromosome;
	private int chromosomeLength;
	private double fitness;
	
	public Individual(int chromosomeLength, City[] cities) {
		setChromosomeLength(chromosomeLength);
		setChromosome();
		setFitness(cities);
	}
	
	public Individual(int[] chromosome, double fitness) {
		setChromosomeLength(chromosome.length);
		setChromosome(chromosome);
		setFitness(fitness);
	}
	
	public Individual(int[] chromosome, City[] cities) {
		setChromosomeLength(chromosome.length);
		setChromosome(chromosome);
		setFitness(cities);
	}

	public int[] getChromosome() {
		return chromosome;
	}	

	public void setChromosome(int[] chromosome) {
		this.chromosome = chromosome;
	}
	
	public void setChromosome() {
		int n = this.chromosomeLength;
		int[] chromosome = new int[n];
		for(int i = 0; i < n; i++) {
			chromosome[i] = i;
		}
		for(int i = 0; i < n-1; i++) {
			int random = i + (int) Math.round(Math.random()*(n-1-i));
			int temp = chromosome[i];
			chromosome[i] = chromosome[random];
			chromosome[random] = temp;
		}
		this.chromosome = chromosome;
	}

	public int getChromosomeLength() {
		return chromosomeLength;
	}

	public void setChromosomeLength(int chromosomeLength) {
		this.chromosomeLength = chromosomeLength;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public void setFitness(City[] cities) {
		IFitness ifit = new Fitness();
		this.fitness = ifit.calculateIndividualFitness(this, cities);
	}
	
	public static Individual[] orderedCrossover(Individual[] parents, City[] cities) {
		int m = parents[0].chromosomeLength;
		Individual[] children = new Individual[2];
		int CX1 = (int) Math.round(Math.random()*(m-3));
		int CX2 = CX1 + (int) Math.round(Math.random()*(m-2-CX1));
		Individual child1 = getChild(parents[0], parents[1], CX1, CX2, cities);
		Individual child2 = getChild(parents[1], parents[0], CX1, CX2, cities);
		children[0] = child1; children[1] = child2;
		return children;
	}
	
	public static Individual getChild(Individual parent1, Individual parent2, int CX1, int CX2, City[] cities) {
		int m = parent1.chromosomeLength;
		int[] child = new int[m];
		Arrays.fill(child, -1);
		int j = CX1 + 1;
		int i = CX1 + 1;
		while(i <= CX2 && j <= CX2) {
			child[j++] = parent1.chromosome[i++];
		}
		j = 0;
		i = CX2 + 1;
		while(j <= CX1) {
			if(!contains(child, parent2.chromosome[i])) {
				child[j++] = parent2.chromosome[i];
			}
			i = (i + 1)%m;
		}
		j = CX2 + 1;
		while(j < m) {
			if(!contains(child, parent2.chromosome[i])) {
				child[j++] = parent2.chromosome[i];
			}
			i = (i + 1)%m;
		}
		return new Individual(child, m);
	}
	
	public Individual swapMutation(double Pm, City[] cities) {
		int m = this.chromosomeLength;
		int[] mutatedChromosome = new int[m];
		for(int i = 0; i < m; i++) {
			mutatedChromosome[i] = this.chromosome[i];
		}
		for(int i = 0; i < m; i++) {
			if(Math.random() < Pm) {
				int temp = mutatedChromosome[i];
				int randIndex = (int) Math.round(Math.random()*(m-1));
				mutatedChromosome[i] = mutatedChromosome[randIndex];
				mutatedChromosome[randIndex] = temp;
			}
		}
		return new Individual(mutatedChromosome, cities);
	}
	
	public static boolean contains(int[] array, int x) {
		boolean result = false;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == x)
				return true;
		}
		return result;
	}
	
	public Individual copyOf() {
		int n = this.chromosomeLength;
		int[] individualCopy = new int[n];
		for(int i = 0; i < n; i++) {
			individualCopy[i] = this.chromosome[i];
		}
		return new Individual(individualCopy, this.fitness);
	}
	
	public String toString() {
		String result = "[";
		for(int i = 0; i < this.chromosomeLength-1; i++) {
			result += "" + this.chromosome[i] + ", ";
		}
		result += "" + this.chromosome[this.chromosomeLength-1] + "]";
		return result;
	}
}
