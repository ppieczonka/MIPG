package test;

import mipg.correctors.ExampleMatrixCorrectorServiceImpl;
import mipg.providers.PrioritizedBasedInitialPopulationProvider;
import org.junit.jupiter.api.Test;

public class PrioritizedBasedInitialPopulationProviderTest {

    @Test
    void checkIfRandomInitialPopulationProviderCreatesPopulationWithProperColumnCounts() {

        int[] vector = {2, 1, 3};

        boolean value = true;
        boolean defaultValue = false;

        var randomInitialPopulationProvider = new PrioritizedBasedInitialPopulationProvider<>(
                5,
                4,
                3,
                new ExampleMatrixCorrectorServiceImpl(),
                vector,
                value,
                defaultValue,
                Boolean.class
        );

        var initialPopulation = randomInitialPopulationProvider.getInitialPopulation();

        assert initialPopulation.size() == 5;
        assert initialPopulation.stream().allMatch(model -> checkColumnTrueCounts(model.getAssigmentMatrix(), vector));
    }

    private static boolean checkColumnTrueCounts(Boolean[][] matrix, int[] expectedCounts) {
        for (int col = 0; col < expectedCounts.length; col++) {
            if (countTrueInColumn(matrix, col) != expectedCounts[col]) {
                return false;
            }
        }
        return true;
    }

    private static long countTrueInColumn(Boolean[][] matrix, int col) {
        long count = 0;
        for (Boolean[] booleans : matrix) {
            if (booleans[col]) {
                count++;
            }
        }
        return count;
    }
}
