//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TravellingSalesman_Unit_Tests {

//	@Test
//	void testCityConstructor() {
//		City city1 = new City(1, 2);
//		assertEquals(1, city1.getxCoordinate());
//		assertEquals(2, city1.getyCoordinate());
//	}
//	
//	@Test
//	void testCityDistanceTo() {
//		City city1 = new City(1, 2);
//		City city2 = new City(1, 2);
//		assertEquals(0.0, city1.distanceTo(city2));
//		
//		City city3 = new City(1, 4);
//		assertEquals(2.0, city1.distanceTo(city3));
//		
//		City city4 = new City(101, 998);
//		assertEquals(1001.01, city1.distanceTo(city4), 2);
//	}
//
//	@Test
//	void testIndividualEmptyConstructor() {
//		City city1 = new City(1, 2);
//		City city2 = new City(3, 4);
//		City city3 = new City(5, 6);
//		
//		City[] cities1 = new City[] {city1, city2, city3};
//		Individual individual1 = new Individual(3, cities1);
//		int sum = 0;
//		for(int i = 0; i < individual1.getChromosomeLength(); i++) {
//			sum += individual1.getChromosome()[i];
//		}
//		assertEquals(3,sum);
//		
//		City city4 = new City(1, 2);
//		City city5 = new City(3, 4);
//		City city6 = new City(5, 6);
//		
//		City[] cities2 = new City[] {city1, city2, city3, city4, city5, city6};
//		Individual individual2 = new Individual(6, cities2);
//		int sum2 = 0;
//		for(int i = 0; i < individual2.getChromosomeLength(); i++) {
//			sum2 += individual2.getChromosome()[i];
//		}
//		assertEquals(15,sum2);
//	}
//	
//	@Test
//	void testGetFittestIndividual() {
//		City city1 = new City(1, 2);
//		City city2 = new City(3, 4);
//		City city3 = new City(5, 6);
//		City[] cities1 = new City[] {city1, city2, city3};
//		Population pop1 = new Population(3, 3, cities1);
//		Individual fittest1 = pop1.getFittest();
//		assertEquals(true, fittest1.getFitness() >= pop1.getPopulation()[0].getFitness());
//		assertEquals(true, fittest1.getFitness() >= pop1.getPopulation()[1].getFitness());
//		assertEquals(true, fittest1.getFitness() >= pop1.getPopulation()[2].getFitness());
//	}
//	
//	@Test
//	void testIndividualCopyOf() {
//		Individual ind1 = new Individual(new int[] {0, 1, 2, 3, 4}, 5.0);
//		Individual copy1 = ind1.copyOf();
//		
//		assertEquals(ind1.toString(), copy1.toString());
//		assertEquals(ind1.getFitness(), copy1.getFitness());
//	}
//	
//	@Test
//	void testGetChildren() {
//		City city1 = new City(1, 2);
//		City city2 = new City(3, 4);
//		City city3 = new City(5, 6);
//		City city4 = new City(1, 7);
//		City city5 = new City(3, 1);
//		City city6 = new City(1, 1);
//		City city7 = new City(2, 2);
//		City[] cities1 = new City[] {city1, city2, city3, city4, city5, city6, city7};
//		Individual parent1 = new Individual(new int[] {0, 1, 2, 3, 4, 5, 6}, cities1);
//		Individual parent2 = new Individual(new int[] {6, 5, 4, 3, 2, 1, 0}, cities1);
//		int CX1 = 0; int CX2 = 3;
//		assertEquals("[0, 1, 2, 3, 6, 5, 4]", Individual.getChild(parent1, parent2, CX1, CX2, cities1).toString());
//		
//		City[] cities2 = new City[] {city1, city2, city3, city4, city5, city6, city7};
//		Individual parent3 = new Individual(new int[] {1, 3, 2, 0, 6, 5, 4}, cities1);
//		Individual parent4 = new Individual(new int[] {0, 5, 4, 2, 3, 6, 1}, cities1);
//		int CX3 = 1; int CX4 = 4;
//		assertEquals("[1, 5, 2, 0, 6, 4, 3]", Individual.getChild(parent3, parent4, CX3, CX4, cities2).toString());
//	}
//	
//	@Test
//	void testIndividualCrossOver() {
//		City city1 = new City(1, 2);
//		City city2 = new City(3, 4);
//		City city3 = new City(5, 6);
//		City city4 = new City(1, 7);
//		City city5 = new City(3, 1);
//		City city6 = new City(1, 1);
//		City city7 = new City(2, 2);
//		City[] cities1 = new City[] {city1, city2, city3, city4, city5, city6, city7};
//		Individual parent1 = new Individual(new int[] {0, 1, 2, 3, 4, 5, 6}, cities1);
//		Individual parent2 = new Individual(new int[] {6, 5, 4, 3, 2, 1, 0}, cities1);
//		Individual[] parents = new Individual[] {parent1, parent2};
//		Individual[] children = Individual.orderedCrossover(parents, cities1);
//		System.out.println("child1: " + children[0]); 		System.out.println("child2: " + children[1]);
//		for(int i = 0; i < cities1.length; i++) {
//			assertEquals(cities1.length-1, children[0].getChromosome()[i] + children[1].getChromosome()[i]);
//		}
//		
//		City[] cities2 = new City[] {city1, city2, city3, city4, city5, city6};
//		Individual parent3 = new Individual(new int[] {3, 0, 1, 4, 2, 5}, cities2);
//		Individual parent4 = new Individual(new int[] {1, 3, 4, 2, 0, 5}, cities2);
//		Individual[] parents2 = new Individual[] {parent3, parent4};
//		Individual[] children2 = Individual.orderedCrossover(parents2, cities2);
//		System.out.println("child1: " + children2[0]); 		System.out.println("child2: " + children2[1]);
//	}
//	
//	@Test
//	void testTournamentSelection() {
//		City city1 = new City(1, 2);
//		City city2 = new City(3, 4);
//		City city3 = new City(5, 6);
//		City[] cities1 = new City[] {city1, city2, city3};
//		Individual ind1 = new Individual(new int[] {0, 1, 2}, cities1);
//		Individual ind2 = new Individual(new int[] {1, 0, 2}, cities1);
//		Individual ind3 = new Individual(new int[] {1, 2, 0}, cities1);
//		Individual ind4 = new Individual(new int[] {2, 1, 0}, cities1);
//		Individual[] inds = new Individual[] {ind1, ind2, ind3, ind4};
//		Population pop1 = new Population(inds);
//		Population tournament1 = pop1.tournamentSelection();
//		for(Individual i: pop1.getPopulation()) {
//			System.out.println(i); 			System.out.println(i.getFitness());
//		}
//		System.out.println(tournament1);
//	}
	
//	@Test
//	void testSortPopulation() {
//		City city1 = new City(1, 2);
//		City city2 = new City(3, 4);
//		City city3 = new City(5, 6);
//		City[] cities1 = new City[] {city1, city2, city3};
//		Individual ind1 = new Individual(new int[] {0, 1, 2}, cities1);
//		Individual ind2 = new Individual(new int[] {1, 0, 2}, cities1);
//		Individual ind3 = new Individual(new int[] {1, 2, 0}, cities1);
//		Individual ind4 = new Individual(new int[] {2, 1, 0}, cities1);
//		Individual[] inds = new Individual[] {ind1, ind2, ind3, ind4};
//		Population pop1 = new Population(inds);
//		Population pop2 = pop1.sortByFitness(); 
//		for(Individual i: pop1.getPopulation()) {
//			System.out.println(i); 			System.out.println(i.getFitness());
//		}
//		for(Individual i: pop2.getPopulation()) {
//			System.out.println(i); 			System.out.println(i.getFitness());
//		}
//		Population pop3 = pop1.sortByFitnessReverse();
//		for(Individual i: pop3.getPopulation()) {
//			System.out.println(i); 			System.out.println(i.getFitness());
//		}
//	}
	
	@Test
	void testReplacePopulation() {
		City city1 = new City(1, 2);
		City city2 = new City(3, 4);
		City city3 = new City(5, 6);
		City[] cities1 = new City[] {city1, city2, city3};
		Individual ind1 = new Individual(new int[] {0, 1, 2}, cities1);
		Individual ind2 = new Individual(new int[] {1, 0, 2}, cities1);
		Individual ind3 = new Individual(new int[] {1, 2, 0}, cities1);
		Individual ind4 = new Individual(new int[] {2, 1, 0}, cities1);
		Individual[] inds = new Individual[] {ind1, ind2, ind3, ind4};
		Population pop1 = new Population(inds);
		Population pop2 = pop1.rouletteWheelSelection();
		Population pop3 = pop1.elitistReplacement(pop2, 0.5);
		for(Individual i: pop1.getPopulation()) {
			System.out.println(i + " -> " + i.getFitness());
		}
		System.out.println("------------");
		for(Individual i: pop2.getPopulation()) {
			System.out.println(i + " -> " + i.getFitness());
		}
		System.out.println("------------");
		for(Individual i: pop3.getPopulation()) {
			System.out.println(i + " -> " + i.getFitness());
		}
	}
}
