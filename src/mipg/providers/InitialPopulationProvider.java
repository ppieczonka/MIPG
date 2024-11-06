package mipg.providers;


import mipg.models.MatrixModel;

import java.util.Set;

public interface InitialPopulationProvider<T> {

    Set<MatrixModel<T>> getInitialPopulation();
}
