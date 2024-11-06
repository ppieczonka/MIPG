package test;

import mipg.correctors.ExampleMatrixCorrectorServiceImpl;
import mipg.providers.GradientBasedInitialPopulationProvider;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class GradientBasedInitialPopulationProviderTest {

    @Test
    void checkIfGradientBasedInitialPopulationProviderCreatesPopulationsWithProperSize() {
        var gradientBasedInitialPopulationProvider = new GradientBasedInitialPopulationProvider<>(
                5,
                100,
                100,
                new ExampleMatrixCorrectorServiceImpl(),
                () -> true,
                () -> false,
                Random::nextBoolean,
                Boolean.class
        );

        var initialPopulation = gradientBasedInitialPopulationProvider.getInitialPopulation();

        assert initialPopulation.size() == 5;
    }

    @Test
    void checkIfGradientBasedInitialPopulationProviderCreatesPopulationsWithProperGradientPattern() {
        var gradientBasedInitialPopulationProvider = new GradientBasedInitialPopulationProvider<>(
                5,
                10,
                10,
                new ExampleMatrixCorrectorServiceImpl(),
                () -> true,
                () -> false,
                Random::nextBoolean,
                Boolean.class
        );

        var initialPopulation = gradientBasedInitialPopulationProvider.getInitialPopulation();

        assert initialPopulation.stream().allMatch(x -> hasValidGradientPattern(x.getAssigmentMatrix()));
    }

    @Test
    void checkIfGradientBasedInitialPopulationProviderCreatesDiversePopulations() {
        var gradientBasedInitialPopulationProvider = new GradientBasedInitialPopulationProvider<>(
                5,
                5,
                5,
                new ExampleMatrixCorrectorServiceImpl(),
                () -> true,
                () -> false,
                Random::nextBoolean,
                Boolean.class
        );

        var initialPopulation = gradientBasedInitialPopulationProvider.getInitialPopulation();

        assert initialPopulation.stream().distinct().count() > 1;
    }

    private boolean hasValidGradientPattern(Boolean[][] matrix) {
        for (int col = 0; col < matrix[0].length; col++) {
            int expectedTrueCount = (matrix.length * (col + 1)) / matrix[0].length;
            long trueCount = countTrueInColumn(matrix, col);

            if (trueCount < expectedTrueCount - 3 || trueCount > expectedTrueCount + 3) {
                return false;
            }
        }
        return true;
    }

    private long countTrueInColumn(Boolean[][] matrix, int col) {
        long count = 0;
        for (Boolean[] booleans : matrix) {
            if (booleans[col]) {
                count++;
            }
        }
        return count;
    }
}