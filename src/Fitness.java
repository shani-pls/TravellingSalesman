
public class Fitness implements IFitness{

	@Override
	public double calculateIndividualFitness(Individual individual, City[] cities) {
		double result = 0.0;
		int numberOfCities = cities.length;
		int[] chromosome = individual.getChromosome();
		for(int i = 0; i < individual.getChromosomeLength()-1; i++) {
			result += cities[chromosome[i]].distanceTo(cities[chromosome[i+1]]);
		}
		return (numberOfCities*numberOfCities)/result;
	}
	
}
