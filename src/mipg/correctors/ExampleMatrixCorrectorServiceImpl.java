package mipg.correctors;

import mipg.models.MatrixModel;

public class ExampleMatrixCorrectorServiceImpl implements GeneticAlgorithmCorrector<Boolean> {

    @Override
    public MatrixModel<Boolean> correct(MatrixModel<Boolean> input) {

        for (int i = 0; i < input.getRowsAmount(); i++) {
            for (int j = 0; j < input.getColumnsAmount(); j++) {
                if (input.getAssigmentMatrix()[i][j] == null) {
                    input.getAssigmentMatrix()[i][j] = false;
                }
            }
        }

        addEntriesIfThereAreLessThanMinimum(input);

        var rowEntryCount = new int[input.getRowsAmount()];
        var columnEntryCount = new int[input.getColumnsAmount()];

        calculateRowEntries(input, rowEntryCount);
        calculateColumnEntries(input, columnEntryCount);

        correctRowsWithNoEntries(input, rowEntryCount);
        correctColumnsWithNoEntries(input, columnEntryCount);

        return input;
    }

    private void addEntriesIfThereAreLessThanMinimum(MatrixModel<Boolean> input) {
        var totalEntries = 0;

        for (int i = 0; i < input.getRowsAmount(); i++) {
            for (int j = 0; j < input.getColumnsAmount(); j++) {
                if (input.getAssigmentMatrix()[i][j]) {
                    totalEntries++;
                }
            }
        }
        var minimumRequiredEntries = Math.max(input.getColumnsAmount(), input.getRowsAmount());
        if (totalEntries < minimumRequiredEntries) {
            for (int i = 0; i < input.getRowsAmount(); i++) {
                for (int j = 0; j < input.getColumnsAmount(); j++) {
                    if (!input.getAssigmentMatrix()[i][j] && totalEntries < minimumRequiredEntries) {
                        input.getAssigmentMatrix()[i][j] = true;
                        totalEntries++;
                    }
                }
            }
        }
    }

    private void correctRowsWithNoEntries(MatrixModel<Boolean> input, int[] rowEntryCount) {
        for (int rowIndex = 0; rowIndex < rowEntryCount.length; rowIndex++) {
            if (rowEntryCount[rowIndex] == 0) {
                var maxEntries = 0;
                var rowIndexWithMaxEntries = 0;
                for (int j = 0; j < rowEntryCount.length; j++) {
                    if (rowEntryCount[j] > maxEntries) {
                        maxEntries = rowEntryCount[j];
                        rowIndexWithMaxEntries = j;
                    }
                }
                rowEntryCount[rowIndex] = 1;
                rowEntryCount[rowIndexWithMaxEntries]--;

                var columnReassigned = false;
                for (int columnIndex = 0; columnIndex < input.getColumnsAmount() && !columnReassigned; columnIndex++) {
                    if (input.getAssigmentMatrix()[rowIndexWithMaxEntries][columnIndex]) {
                        input.getAssigmentMatrix()[rowIndexWithMaxEntries][columnIndex] = false;
                        input.getAssigmentMatrix()[rowIndex][columnIndex] = true;
                        columnReassigned = true;
                    }
                }
            }
        }
    }

    private void correctColumnsWithNoEntries(MatrixModel<Boolean> input, int[] columnEntryCount) {
        for (int columnIndex = 0; columnIndex < columnEntryCount.length; columnIndex++) {
            if (columnEntryCount[columnIndex] == 0) {
                var maxEntries = 0;
                var columnIndexWithMaxEntries = 0;
                for (int j = 0; j < columnEntryCount.length; j++) {
                    if (columnEntryCount[j] > maxEntries) {
                        maxEntries = columnEntryCount[j];
                        columnIndexWithMaxEntries = j;
                    }
                }
                columnEntryCount[columnIndex] = 1;
                columnEntryCount[columnIndexWithMaxEntries]--;

                var rowReassigned = false;
                for (int rowIndex = 0; rowIndex < input.getRowsAmount() && !rowReassigned; rowIndex++) {
                    if (input.getAssigmentMatrix()[rowIndex][columnIndexWithMaxEntries]) {
                        input.getAssigmentMatrix()[rowIndex][columnIndexWithMaxEntries] = false;
                        input.getAssigmentMatrix()[rowIndex][columnIndex] = true;
                        rowReassigned = true;
                    }
                }
            }
        }
    }

    private void calculateRowEntries(MatrixModel<Boolean> input, int[] rowEntryCount) {
        for (int i = 0; i < input.getRowsAmount(); i++) {
            var entriesCounter = 0;
            for (int j = 0; j < input.getColumnsAmount(); j++) {
                if (input.getAssigmentMatrix()[i][j]) {
                    entriesCounter++;
                }
            }
            rowEntryCount[i] = entriesCounter;
        }
    }

    private void calculateColumnEntries(MatrixModel<Boolean> input, int[] columnEntryCount) {
        for (int i = 0; i < input.getColumnsAmount(); i++) {
            var entriesCounter = 0;
            for (int j = 0; j < input.getRowsAmount(); j++) {
                if (input.getAssigmentMatrix()[j][i]) {
                    entriesCounter++;
                }
            }
            columnEntryCount[i] = entriesCounter;
        }
    }
}