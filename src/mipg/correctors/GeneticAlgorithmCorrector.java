package mipg.correctors;


import mipg.models.MatrixModel;

public interface GeneticAlgorithmCorrector<T> {
    MatrixModel<T> correct(MatrixModel<T> input);
}
