package mipg.models;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public class MatrixModel<T> {

    private T[][] assigmentMatrix;

    public MatrixModel(T[][] assigmentMatrix) {
        this.assigmentMatrix = assigmentMatrix;
    }

    @SuppressWarnings("unchecked")
    public MatrixModel(Class<T> clazz, int rows, int columns) {
        this.assigmentMatrix = (T[][]) Array.newInstance(clazz, rows, columns);
    }

    public T[][] getAssigmentMatrix() {
        return assigmentMatrix;
    }

    public void setAssigmentMatrix(T[][] assigmentMatrix) {
        this.assigmentMatrix = assigmentMatrix;
    }

    public int getRowsAmount() {
        return assigmentMatrix.length;
    }

    public int getColumnsAmount() {
        return assigmentMatrix[0].length;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatrixModel)) return false;
        MatrixModel<T> that = (MatrixModel<T>) o;
        return Arrays.deepEquals(that.getAssigmentMatrix(), getAssigmentMatrix());
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(getAssigmentMatrix()));
    }
}
