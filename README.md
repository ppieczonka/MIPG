
# MIPG - Java Library for Initial Population Generation in Genetic Algorithms

**MIPG** is an open-source Java library designed to provide flexible strategies for generating initial populations in genetic algorithms (GA). This library offers various population generation strategies that can be customized to fit a wide range of optimization problems, making it suitable for applications in engineering, bioinformatics, artificial intelligence, and more.

## Usage

1. **Install Java 8 or higher**.
2. **Set up MIPG**: Clone the repository and import it into your Java project.

   ```java
   // Example of using RandomInitialPopulationProvider
   var randomInitialPopulationProvider = new RandomInitialPopulationProvider<>(
       5, // population size
       10, // rows
       10, // columns
       new ExampleMatrixCorrectorServiceImpl(), // corrector service
       Random::nextBoolean, // random generator
       Boolean.class // data type
   );

   var initialPopulation = randomInitialPopulationProvider.getInitialPopulation();
   ```

3. **Choose Population Provider**: Select or customize a provider that suits your problem domain.
4. **Customize with Correctors**: Implement `GeneticAlgorithmCorrector` interface if further constraints are needed.

## Requirements

- Java 8+
- Functional interfaces for custom population generation strategies

## License

MIPG is released under the MIT License. See `LICENSE` for more details.
