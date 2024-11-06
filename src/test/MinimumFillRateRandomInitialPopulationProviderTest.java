package test;

import mipg.correctors.ExampleMatrixCorrectorServiceImpl;
import mipg.providers.MinimumFillRateRandomInitialPopulationProvider;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MinimumFillRateRandomInitialPopulationProviderTest {

    @Test
    void checkIfFillRateRandomInitialPopulationProviderPreparesPopulationsWithProperFillRate() {
        var fillRateRandomInitialPopulationProvider = new MinimumFillRateRandomInitialPopulationProvider<Boolean>(
                5,
                10,
                10,
                60,
                new ExampleMatrixCorrectorServiceImpl(),
                (x) -> amountOfAssignments(x.getAssigmentMatrix()),
                (y) -> true,
                Boolean.class
        );

        var initialPopulation = fillRateRandomInitialPopulationProvider.getInitialPopulation();

        assert initialPopulation.size() == 5;
        assert initialPopulation.stream().allMatch(x -> amountOfAssignments(x.getAssigmentMatrix()) >= 60);
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
