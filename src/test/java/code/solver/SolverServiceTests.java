package code.solver;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SolverServiceTests {
    SolverService service = new SolverService();


    @Test
    public void extractCoefficientsTest() throws SolverException {
        var result = service.extractCoefficients("10x2+x+1=0\n");
        assertEquals(result, Arrays.asList(10, 1, 1));
        result = service.extractCoefficients("x2+x+1=0");
        assertEquals(result, Arrays.asList(1, 1, 1));
        result = service.extractCoefficients("5x2-5x+10=0");
        assertEquals(result, Arrays.asList(5, -5, 10));
        result = service.extractCoefficients("500x2-1999x+299=0");
        assertEquals(result, Arrays.asList(500, -1999, 299));
    }

    @Test
    public void solverTest() throws SolverException {
        var r = service.solve(5, 6, 1);
        assertTrue(Math.abs(r.get(1) + 0.2) < 0.0001);
        assertTrue(Math.abs(r.get(0) + 1) < 0.0001);

        var exception = assertThrows(SolverException.class,
                () -> service.solve(1, 1, 1));

        assertEquals(exception.getMessage(), "No real solution.");

        exception = assertThrows(SolverException.class,
                () -> service.solve(0, 1, 1));

        assertEquals(exception.getMessage(), "Not a quadratic equation.");
    }
}
