package test;

import mipg.correctors.ExampleMatrixCorrectorServiceImpl;
import mipg.providers.RandomInitialPopulationProvider;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class RandomInitialPopulationProviderTest {

    @Test
    void checkIfFillRateRandomInitialPopulationProviderPreparesPopulationsWithProperFillRate() {
        var randomInitialPopulationProviderTest = new RandomInitialPopulationProvider<>(
                5,
                10,
                10,
                new ExampleMatrixCorrectorServiceImpl(),
                Random::nextBoolean,
                Boolean.class
        );

        var initialPopulation = randomInitialPopulationProviderTest.getInitialPopulation();

        assert initialPopulation.size() == 5;
        assert initialPopulation.stream().allMatch(x -> x.getAssigmentMatrix().length == 10);
        assert initialPopulation.stream().allMatch(x -> x.getAssigmentMatrix()[0].length == 10);
    }
}
