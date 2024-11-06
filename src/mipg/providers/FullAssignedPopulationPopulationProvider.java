package mipg.providers;


import mipg.correctors.GeneticAlgorithmCorrector;
import mipg.models.MatrixModel;

import java.lang.reflect.Array;
import java.util.Set;

public class FullAssignedPopulationPopulationProvider<T> implements InitialPopulationProvider<T> {

    private final int rows;
    private final int columns;
    private final GeneticAlgorithmCorrector<T> corrector;

    private final T expectedValue;

    private final Class<T> clazz;

    public FullAssignedPopulationPopulationProvider(int rows, int columns, GeneticAlgorithmCorrector<T> corrector, T expectedValue, Class<T> clazz) {
        this.rows = rows;
        this.columns = columns;
        this.corrector = corrector;
        this.expectedValue = expectedValue;
        this.clazz = clazz;

    }

    @Override
    public Set<MatrixModel<T>> getInitialPopulation() {
        T[][] matrix = (T[][]) Array.newInstance(clazz, rows, columns);
        MatrixModel<T> output = new MatrixModel<>(matrix);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                output.getAssigmentMatrix()[i][j] = expectedValue;
            }
        }

        output = corrector.correct(output);
        return Set.of(output);
    }
}
