package test;

import mipg.correctors.ExampleMatrixCorrectorServiceImpl;
import mipg.models.MatrixModel;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ExampleMatrixCorrectorServiceImplTest {

    ExampleMatrixCorrectorServiceImpl corrector = new ExampleMatrixCorrectorServiceImpl();

    @Test
    public void checkIfCorrectorIsNotChangingMatrixIfIsCorrect() {
        var givenFirstAssigmentMatrix = new Boolean[][]{
                {false, false, true, false, false, true, false, false, false, false, false, false, false, false},
                {false, true, false, false, false, false, false, false, true, false, false, false, true, false},
                {false, false, false, false, true, false, false, true, false, true, false, true, false, false},
                {true, false, true, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, true, true, false, false, true, false, false, false, true, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, true}
        };

        var givenFirstAssigmentModel = new MatrixModel<>(givenFirstAssigmentMatrix);
        corrector.correct(givenFirstAssigmentModel);

        assert isDataInvalid(givenFirstAssigmentModel);
        assert Arrays.deepEquals(givenFirstAssigmentMatrix, new Boolean[][]{
                {false, false, true, false, false, true, false, false, false, false, false, false, false, false},
                {false, true, false, false, false, false, false, false, true, false, false, false, true, false},
                {false, false, false, false, true, false, false, true, false, true, false, true, false, false},
                {true, false, true, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, true, true, false, false, true, false, false, false, true, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, true}
        });
    }

    @Test
    public void checkIfBothCasesAreCorrected() {
        var givenFirstAssigmentMatrix = new Boolean[][]{
                {false, false, true, false, false, true, false, false, false, false, false, false, false, false},
                {false, true, false, false, false, false, false, false, true, false, false, false, true, false},
                {false, false, false, false, true, false, false, true, false, true, false, true, false, false},
                {true, false, true, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, true, true, false, false, true, false, false, false, true, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false}
        };

        buildInputCorrectAndValidate(givenFirstAssigmentMatrix);
    }

    @Test
    public void checkIfAssignmentToInspectorWorks() {
        var givenFirstAssigmentMatrix = new Boolean[][]{
                {false, false, true, false, false, true, false, false, false, false, false, false, false, true},
                {false, true, false, false, false, false, false, false, true, false, false, false, true, false},
                {false, false, false, false, true, false, false, true, false, true, false, true, false, false},
                {true, false, true, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, true, true, false, false, true, false, false, false, true, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false}
        };

        buildInputCorrectAndValidate(givenFirstAssigmentMatrix);
    }

    @Test
    public void checkIfCorrectingObjectCoverageWorks() {
        var givenFirstAssigmentMatrix = new Boolean[][]{
                {false, false, true, false, false, true, false, false, false, false, false, false, false, false},
                {false, true, false, false, false, false, false, false, true, false, false, false, true, false},
                {false, false, false, false, true, false, false, true, false, true, false, true, false, false},
                {true, false, true, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, true, true, false, false, true, false, false, false, true, false, false, false},
                {false, false, false, false, false, true, false, false, false, false, false, false, false, false}
        };

        buildInputCorrectAndValidate(givenFirstAssigmentMatrix);
    }

    @Test
    public void checkIfCorrectsProperlyIfOnlyOneInspectorHasAssignment() {
        var givenFirstAssigmentMatrix = new Boolean[][]{
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, true, true, true, true, true, true}
        };

        buildInputCorrectAndValidate(givenFirstAssigmentMatrix);
    }

    @Test
    public void checkIfCorrectsProperlyIfOnlyOneObjectHasCoverage() {
        var givenFirstAssigmentMatrix = new Boolean[][]{
                {false, false, false, false, false, false, true, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, true, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, true, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, true, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, true, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, true, false, false, false, false, false, false, false}
        };

        buildInputCorrectAndValidate(givenFirstAssigmentMatrix);
    }

    @Test
    public void checkIfAddMissingElements() {
        var givenFirstAssigmentMatrix = new Boolean[][]{
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false}
        };

        buildInputCorrectAndValidate(givenFirstAssigmentMatrix);
    }

    @Test
    public void checkSomeMatrix() {
        var givenFirstAssigmentMatrix = new Boolean[][]{
                {false, true, false, false, false, false, false, false, false, false, false, false, true, false},
                {false, true, false, false, false, false, false, false, false, false, false, false, true, false},
                {false, true, false, false, false, false, false, false, false, false, false, false, true, false},
                {false, true, false, false, false, false, false, false, false, false, false, false, true, false},
                {false, true, false, false, false, false, false, false, false, false, false, false, true, false},
                {false, true, false, false, false, false, false, false, false, false, false, false, true, false}
        };

        buildInputCorrectAndValidate(givenFirstAssigmentMatrix);
    }

    @Test
    public void checkSomeMatrix2() {
        var givenFirstAssigmentMatrix = new Boolean[][]{
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, true, true, true, true, true, true, true},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, false, false, false, false, false, false}
        };

        buildInputCorrectAndValidate(givenFirstAssigmentMatrix);
    }


    private void buildInputCorrectAndValidate(Boolean[][] givenFirstAssigmentMatrix) {
        var givenFirstAssigmentModel = new MatrixModel<>(givenFirstAssigmentMatrix);
        givenFirstAssigmentModel = corrector.correct(givenFirstAssigmentModel);

        assert isDataInvalid(givenFirstAssigmentModel);
    }

    private Boolean isDataInvalid(MatrixModel<Boolean> patientAssigmentModel) {
        if (doesAnyObjectHaveNoAssignment(patientAssigmentModel)) return false;
        return !doesAnyInspectorHaveNoAssignment(patientAssigmentModel);
    }

    private Boolean doesAnyObjectHaveNoAssignment(MatrixModel<Boolean> patientAssigmentModel) {
        var assigmentMatrix = patientAssigmentModel.getAssigmentMatrix();

        for (int i = 0; i < patientAssigmentModel.getRowsAmount(); i++) {
            var inspectorAssignedToPatientCounter = 0;
            for (int j = 0; j < patientAssigmentModel.getColumnsAmount(); j++) {
                if (assigmentMatrix[i][j]) {
                    inspectorAssignedToPatientCounter++;
                }
            }
            if (inspectorAssignedToPatientCounter == 0) {
                System.out.println("validation not passed because instructor with index " + i + " has no object assigned.");
                return true;
            }
        }
        return false;
    }

    private Boolean doesAnyInspectorHaveNoAssignment(MatrixModel<Boolean> patientAssigmentModel) {
        var assigmentMatrix = patientAssigmentModel.getAssigmentMatrix();

        for (int i = 0; i < patientAssigmentModel.getColumnsAmount(); i++) {
            var inspectorsWithNoAssignment = 0;
            for (int j = 0; j < patientAssigmentModel.getRowsAmount(); j++) {
                if (assigmentMatrix[j][i]) {
                    inspectorsWithNoAssignment++;
                }
            }
            if (inspectorsWithNoAssignment == 0) {
                System.out.println("Validation not passed because there is no inspector assigned to patient with index " + i);
                return true;
            }
        }
        return false;
    }
}
