package mipg.providers;


import mipg.correctors.GeneticAlgorithmCorrector;
import mipg.models.MatrixModel;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class PrioritizedBasedInitialPopulationProvider<T> implements InitialPopulationProvider<T> {

    private final int initialSize;
    private final int rows;
    private final int columns;
    private final GeneticAlgorithmCorrector<T> corrector;
    private final int[] vector;
    private final T value;
    private final T defaultValue;

    private final Class<T> clazz;

    public PrioritizedBasedInitialPopulationProvider(
            int initialSize,
            int rows,
            int columns,
            GeneticAlgorithmCorrector<T> corrector,
            int[] vector,
            T value,
            T defaultValue,
            Class<T> clazz
    ) {
        if (vector.length != columns) {
            throw new IllegalArgumentException("Length of vector must match the number of columns.");
        }

        this.initialSize = initialSize;
        this.rows = rows;
        this.columns = columns;
        this.corrector = corrector;
        this.vector = vector;
        this.value = value;
        this.defaultValue = defaultValue;
        this.clazz = clazz;
    }

    @Override
    public Set<MatrixModel<T>> getInitialPopulation() {

        var random = new Random();
        HashSet<MatrixModel<T>> results = new HashSet<>();

        for (int i = 0; i < initialSize; i++) {
            T[][] matrix = (T[][]) Array.newInstance(clazz, rows, columns);
            MatrixModel<T> newModel = new MatrixModel<>(matrix);

            for (int z = 0; z < columns; z++) {
                Set<Integer> selectedRows = new HashSet<>();
                while (selectedRows.size() < vector[z]) {
                    selectedRows.add(random.nextInt(rows));
                }

                for (int j = 0; j < rows; j++) {
                    newModel.getAssigmentMatrix()[j][z] = selectedRows.contains(j) ? value : defaultValue;
                }
            }

            results.add(newModel);
        }
        return results.stream()
                .map(corrector::correct)
                .collect(Collectors.toSet());
    }
}