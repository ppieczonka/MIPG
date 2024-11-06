package mipg.correctors;

import mipg.models.MatrixModel;

public class BlankMatrixCorrectorServiceImpl<T> implements GeneticAlgorithmCorrector<T> {
    @Override
    public MatrixModel<T> correct(MatrixModel<T> input) {
        return input;
    }
}
