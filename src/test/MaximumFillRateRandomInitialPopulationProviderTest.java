package test;

import mipg.correctors.ExampleMatrixCorrectorServiceImpl;
import mipg.providers.MaximumFillRateRandomInitialPopulationProvider;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MaximumFillRateRandomInitialPopulationProviderTest {

    @Test
    void checkIfFillRateRandomInitialPopulationProviderPreparesPopulationsWithProperFillRate() {
        var fillRateRandomInitialPopulationProvider = new MaximumFillRateRandomInitialPopulationProvider<>(
                5,
                20,
                10,
                60,
                new ExampleMatrixCorrectorServiceImpl(),
                (x) -> amountOfAssignments(x.getAssigmentMatrix()),
                Random::nextBoolean,
                Boolean.class
        );

        var initialPopulation = fillRateRandomInitialPopulationProvider.getInitialPopulation();

        assert initialPopulation.size() == 5;
        assert initialPopulation.stream().allMatch(x -> amountOfAssignments(x.getAssigmentMatrix()) <= 120);
    }

    private static int amountOfAssignments(Boolean[][] matrix) {
        return (int) flattenToList(matrix).stream()
                .filter(x -> x)
                .count();
    }

    public static List<Boolean> flattenToList(Boolean[][] twoDimArray) {
        List<Boolean> list = new ArrayList<>();

        for (Boolean[] row : twoDimArray) {
            list.addAll(Arrays.asList(row));
        }

        list = list.stream().map(x -> x != null && x).collect(Collectors.toList());
        return list;
    }
}
