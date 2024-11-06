package mipg.providers;


import mipg.correctors.GeneticAlgorithmCorrector;
import mipg.models.MatrixModel;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GradientBasedInitialPopulationProvider<T> implements InitialPopulationProvider<T> {

    private final int initialSize;
    private final int rows;
    private final int columns;
    private final GeneticAlgorithmCorrector<T> corrector;
    private final Random random;
    private final Function<Random, T> randomValueReceiver;
    private final Supplier<T> expectedValueReceiver;
    private final Supplier<T> unexpectedValueReceiver;

    private final Class<T> clazz;

    public GradientBasedInitialPopulationProvider(
            int initialSize,
            int rows,
            int columns,
            GeneticAlgorithmCorrector<T> corrector,
            Supplier<T> expectedValueReceiver,
            Supplier<T> unexpectedValueReceiver,
            Function<Random, T> randomValueReceiver,
            Class<T> clazz
    ) {
        this.initialSize = initialSize;
        this.rows = rows;
        this.columns = columns;
        this.corrector = corrector;
        this.random = new Random();
        this.randomValueReceiver = randomValueReceiver;
        this.expectedValueReceiver = expectedValueReceiver;
        this.unexpectedValueReceiver = unexpectedValueReceiver;
        this.clazz = clazz;

    }

    @Override
    public Set<MatrixModel<T>> getInitialPopulation() {
        HashSet<MatrixModel<T>> results = new HashSet<>();

        for (int i = 0; i < initialSize; i++) {
            T[][] matrix = (T[][]) Array.newInstance(clazz, rows, columns);
            MatrixModel<T> newModel = new MatrixModel<>(matrix);
            initializeWithGradientAndRandomness(newModel);
            results.add(newModel);
        }

        return results.stream()
                .map(corrector::correct)
                .collect(Collectors.toSet());
    }

    private void initializeWithGradientAndRandomness(MatrixModel<T> model) {
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                boolean useGradient = random.nextDouble() < 0.8;
                if (useGradient && row < (rows * (col + 1)) / columns) {
                    model.getAssigmentMatrix()[row][col] = expectedValueReceiver.get();
                } else if (useGradient) {
                    model.getAssigmentMatrix()[row][col] = unexpectedValueReceiver.get();
                } else {
                    model.getAssigmentMatrix()[row][col] = randomValueReceiver.apply(random);
                }
            }
        }
    }
}