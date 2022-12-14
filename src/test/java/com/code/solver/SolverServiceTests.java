package com.code.solver;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SolverServiceTests {
    SolverService service = new SolverService();


    @Test
    public void extractCoefficients() throws SolverException {
        var result = service.extractCoefficients("10x2+x+1=0\n");
        assertEquals(result, Arrays.asList(10, 1, 1));
        result = service.extractCoefficients("x2+x+1=0");
        assertEquals(result, Arrays.asList(1, 1, 1));
        result = service.extractCoefficients("5x2-5x+10=0");
        assertEquals(result, Arrays.asList(5, -5, 10));
        result = service.extractCoefficients("500x2-1999x+299=0");
        assertEquals(result, Arrays.asList(500, -1999, 299));
        result = service.extractCoefficients("-5x2 - 3x - 1 = 0");
        assertEquals(result, Arrays.asList(-5, -3, -1));
        result = service.extractCoefficients("1x2 + 0x - 4 = 0");
        assertEquals(result, Arrays.asList(1, 0, -4));
        result = service.extractCoefficients("1x2 + 0x = 0");
        assertEquals(result, Arrays.asList(1, 0, 0));

        // these cases should pass unit testing.
        result = service.extractCoefficients("x2 - 4 = 0");
        assertEquals(result, Arrays.asList(1, 0, -4));
        result = service.extractCoefficients("2x2 - 4x = 0");
        assertEquals(result, Arrays.asList(2, -4, 0));
        result = service.extractCoefficients("x2 + x + 1 = 0");
        assertEquals(result, Arrays.asList(1, 1, 1));
        result = service.extractCoefficients("x2 - x - 1 = 0");
        assertEquals(result, Arrays.asList(1, -1, -1));
        result = service.extractCoefficients("+x2 + x + 1 = 0");
        assertEquals(result, Arrays.asList(1, 1, 1));
        result = service.extractCoefficients("-x2 - x - 1 = 0");
        assertEquals(result, Arrays.asList(-1, -1, -1));
        result = service.extractCoefficients("x2 + x = 0");
        assertEquals(result, Arrays.asList(1, 1, 0));
        result = service.extractCoefficients("x2 - x = 0");
        assertEquals(result, Arrays.asList(1, -1, 0));
    }

    @Test
    public void extractCoefficientsNegativeCases() {
        for (String eq:Arrays.asList("", "x+1=0", "-x-1", "2-3=0")) {
            var exception = assertThrows(SolverException.class, () ->
                    service.extractCoefficients(eq));
            assertEquals(exception.getMessage(), "Not a quadratic equation.");
        }
    }

    @Test
    public void solver() throws SolverException {
        var r = service.solve(5, 6, 1);
        assertTrue(Math.abs(r.get(1) + 0.2) < 0.0001);
        assertTrue(Math.abs(r.get(0) + 1) < 0.0001);

        r = service.solve(1, 2, 1);
        assertTrue(Math.abs(r.get(1) + 1) < 0.0001);
        assertTrue(Math.abs(r.get(0) + 1) < 0.0001);

        var exception = assertThrows(SolverException.class,
                () -> service.solve(1, 1, 1));

        assertEquals(exception.getMessage(), "No real solution.");

        exception = assertThrows(SolverException.class,
                () -> service.solve(0, 1, 1));

        assertEquals(exception.getMessage(), "Not a quadratic equation.");
    }
}
