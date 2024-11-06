package mipg.providers;


import mipg.correctors.GeneticAlgorithmCorrector;
import mipg.models.MatrixModel;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MinimumFillRateRandomInitialPopulationProvider<T> implements InitialPopulationProvider<T> {

    private final int initialSize;
    private final int rows;
    private final int columns;
    private final int fillRate;
    private final GeneticAlgorithmCorrector<T> corrector;

    private final Function<MatrixModel<T>, Integer> function;

    private final Function<Random, T> producer;

    private final Class<T> clazz;

    public MinimumFillRateRandomInitialPopulationProvider(
            int initialSize,
            int rows,
            int columns,
            int fillRate,
            GeneticAlgorithmCorrector<T> corrector,
            Function<MatrixModel<T>, Integer> function,
            Function<Random, T> producer,
            Class<T> clazz
    ) {
        this.initialSize = initialSize;
        this.rows = rows;
        this.columns = columns;
        this.corrector = corrector;
        if (fillRate < 0) {
            this.fillRate = 0;
        } else {
            this.fillRate = Math.min(fillRate, 100);
        }
        this.function = function;
        this.producer = producer;
        this.clazz = clazz;
    }

    @Override
    public Set<MatrixModel<T>> getInitialPopulation() {

        var random = new Random();
        HashSet<MatrixModel<T>> results = new HashSet<>();
        int assignmentsAmount = (int) (rows * columns * (float) fillRate / 100);
        for (int i = 0; i < initialSize; i++) {
            T[][] matrix = (T[][]) Array.newInstance(clazz, rows, columns);
            MatrixModel<T> newModel = new MatrixModel<>(matrix);
            while (function.apply(newModel) <= assignmentsAmount) {
                newModel.getAssigmentMatrix()[random.nextInt(rows)][random.nextInt(columns)] = producer.apply(null);
            }
            results.add(newModel);
        }
        return results.stream()
                .map(corrector::correct)
                .collect(Collectors.toSet());
    }
}
