package test;

import mipg.correctors.ExampleMatrixCorrectorServiceImpl;
import mipg.providers.FullAssignedPopulationPopulationProvider;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FullAssignedPopulationPopulationProviderTest {
    @Test
    void checkIfFullAssignedPopulationPopulationProviderTestsWithProperFillRate() {
        var fillRateRandomInitialPopulationProvider = new FullAssignedPopulationPopulationProvider<>(
                5,
                10,
                new ExampleMatrixCorrectorServiceImpl(),
                true,
                Boolean.class
        );

        var initialPopulation = fillRateRandomInitialPopulationProvider.getInitialPopulation();

        assert initialPopulation.size() == 1;
        assert initialPopulation.stream().allMatch(x -> amountOfAssignments(x.getAssigmentMatrix()) == 50);
    }

    private static long amountOfAssignments(Boolean[][] matrix) {
        return flattenToList(matrix).stream()
                .filter(x -> x)
                .count();
    }

    public static List<Boolean> flattenToList(Boolean[][] twoDimArray) {
        List<Boolean> list = new ArrayList<>();

        for (Boolean[] row : twoDimArray) {
            list.addAll(Arrays.asList(row));
        }
        return list;
    }
}