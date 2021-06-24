# TravellingSalesman

Travelling Salesman Problem solution using Genetic Algorithms

## Description

The travelling salesman problem (TSP) is a very well-known NP- hard problem that has been studied since the 1800's, for which there is still no approach that guarantees an optimal solution for large datasets. The concept behind TSP is relatively simple since the aim is as follows: Given a collection of cities, find the optimal route that passes once through all of these cities such that the total distance travelled is minimized. The figure below shows a visual representation of the problem.

![Image showing an example of TSP](https://www.lancaster.ac.uk/stor-i-student-sites/libby-daniells/wp-content/uploads/sites/9/2020/04/TSP3-940x529.png)

Although TSP is easy to solve with a brute force algorithm, the factorial space-time complexity of TSP makes such an approach intractable. With only 10 cities in the search space, there are already 3,628,800 potential routes and with 30 cities there are 10<sup>32</sup> possible routes. 

Despite the fact that TSP typically deals with the navigation of cities, there are many other real-world applications such as circuit board drilling and genome sequencing, which can have the equivalent of thousands to millions of cities. To date there is no polynomial-time algorithm that can solve this problem and, as such, the TSP problem remains one of the most widely studied optimization problems.

Even though there is no method that guarantees an optimal solution, there are a number of approaches that can find solutions that are near optimal. Nearest neighbour, greedy approach and genetic algorithms (GA) are some examples of algorithms that are capable of finding solutions that are within a few percent of the optimal solution. It was decided that a genetic algorithm would be used for this project.

## Getting Started

### Dependencies

* Java 8 is the only requirement. 

### Installing and using

* Simply clone the code from this repository. If using Eclipe, IntelliJ or another IDE, Main.java can be run. 
* If running on terminal, src code needs to be compiled before running client. This can be done by navigating into the src directory of the cloned the repository. Then run: ```javac -d ./../bin -sourcepath . Main.java ```. Then the application can be run from the bin directory with the following command: ```java Main```
* Once the program is run, it will prompt you to enter number of cities, population size, number of generations, crossover probability and mutation probability. See below for an example:
  ```
  >> java Main
     Enter in the following order:
     number of cities,
     population size,
     number of generations,
     crossover probability,
     mutation probability
  >> 10
  >> 100
  >> 3
  >> 0.8
  >> 0.1
  ```
* The program will return the best individual (path with the least distance) at each iteration and will save the path coordinates of the best path at each iteration to a text file. See below the result from the provided example:
  ```
  printing paths
  printing fitness values
  printing cities
  Fittest: 0.0296
  Least distance: 3380.0212
  printing paths
  printing fitness values
  Fittest: 0.0306
  Least distance: 3273.2531
  printing paths
  printing fitness values
  Fittest: 0.0333
  Least distance: 3004.1995
  printing paths
  printing fitness values
  Fittest: 0.0366
  Least distance: 2728.7417
  ```
* It is important to note that coordinates of the cities will be randomly generated each time that this function is run.
   

