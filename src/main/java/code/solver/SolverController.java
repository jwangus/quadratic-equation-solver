package code.solver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/")
public class SolverController {

    @Autowired
    SolverService solverService;

    @RequestMapping(value = "quadratic-equation-solver/{equation}")
    ResponseEntity<SolverResponse> solve(@PathVariable("equation") String equation) {

        return ResponseEntity.ok(solverService.solve(equation));
    }
}
