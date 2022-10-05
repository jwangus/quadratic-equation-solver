package com.code.solver;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SolverControllerTests {
    SolverController controller = new SolverController();

    @Test
    public void solveWithParams() {
        controller.solverService = new SolverService();

        var result = controller.solve("1", "2", "1");
        assertEquals (result.getBody().getDescription(), "Solved.");

        result = controller.solve("1", "aaa", "1");
        assertEquals (result.getBody().getDescription(), "Invalid coefficients.");

        result = controller.solve("0", "1", "1");
        assertEquals (result.getBody().getDescription(), "Not a quadratic equation.");
    }
}
