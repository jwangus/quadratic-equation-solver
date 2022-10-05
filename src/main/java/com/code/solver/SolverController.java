package com.code.solver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/v1/")
public class SolverController {
    private static final Logger logger = LoggerFactory.getLogger(SolverController.class);
    @Autowired
    SolverService solverService;

    @RequestMapping(value = "quadratic-equation-solver/text/{equation}")
    ResponseEntity<SolverResponse> solve(@PathVariable("equation") String equation) {

        return ResponseEntity.ok(solverService.solve(equation));
    }

    @RequestMapping(value = "quadratic-equation-solver/coefficients")
    ResponseEntity<SolverResponse> solve(@RequestParam("a") String paramA, @RequestParam("b") String paramB, @RequestParam("c") String paramC) {
        try {
            var a = Integer.parseInt(paramA);
            var b = Integer.valueOf(paramB);
            var c = Integer.valueOf(paramC);
            var result = solverService.solve(a, b, c);
            return ResponseEntity.ok(new SolverResponse(result, "", "Solved."));
        } catch (NumberFormatException e) {
            logger.warn("", e);
            return ResponseEntity.ok(new SolverResponse(new ArrayList<>(), "", "Invalid coefficients."));
        } catch (SolverException e) {
            return ResponseEntity.ok(new SolverResponse(new ArrayList<>(), "", e.getMessage()));
        }
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<SolverResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new SolverResponse(new ArrayList<>(), "", "Missing coefficients."));
    }

}
