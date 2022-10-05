package com.code.solver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Setter @Getter @ToString
public class SolverResponse {
    private List<Double> answers;
    private String equation;
    private String description;
}
