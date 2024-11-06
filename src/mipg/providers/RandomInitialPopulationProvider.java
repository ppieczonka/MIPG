package mipg.providers;


import mipg.correctors.GeneticAlgorithmCorrector;
import mipg.models.MatrixModel;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RandomInitialPopulationProvider<T> implements InitialPopulationProvider<T> {

    private final int initialSize;
    private final int rows;
    private final int columns;
    private final GeneticAlgorithmCorrector<T> corrector;
    private final Function<Random, T> producer;
    private final Class<T> clazz;

    public RandomInitialPopulationProvider(
            int initialSize,
            int rows,
            int columns,
            GeneticAlgorithmCorrector<T> corrector,
            Function<Random, T> producer,
            Class<T> clazz
    ) {
        this.initialSize = initialSize;
        this.rows = rows;
        this.columns = columns;
        this.corrector = corrector;
        this.producer = producer;
        this.clazz = clazz;
    }

    @Override
    public Set<MatrixModel<T>> getInitialPopulation() {

        var random = new Random();
        HashSet<MatrixModel<T>> results = new HashSet<>();

        for (int i = 0; i < initialSize; i++) {
            T[][] matrix = (T[][]) Array.newInstance(clazz, rows, columns);
            MatrixModel<T> newModel = new MatrixModel<>(matrix);
            for (int j = 0; j < rows; j++) {
                for (int z = 0; z < columns; z++) {
                    newModel.getAssigmentMatrix()[j][z] = producer.apply(random);
                }
            }

            results.add(newModel);
        }
        return results.stream()
                .map(corrector::correct)
                .collect(Collectors.toSet());
    }
}
