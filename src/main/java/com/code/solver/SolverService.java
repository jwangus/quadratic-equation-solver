package com.code.solver;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SolverService {
    public SolverResponse solve(String equation) {
        try {
            List<Integer> coefficients = extractCoefficients(equation);
            List<Double> results = solve(coefficients.get(0), coefficients.get(1), coefficients.get(2));
            return new SolverResponse(results, equation, "Solved.");
        } catch (SolverException e) {
            return new SolverResponse(new ArrayList<>(), equation, e.getMessage());
        }
    }

    public static String translate(String str) {
        if (str == null) {
            return "0";
        } else if (str.equals("")) {
            return "1";
        } else if (str.equals("+")) {
            return "+1";
        } else if (str.equals("-")) {
            return "-1";
        } else {
            return str;
        }
    }

    static Pattern EQ_PATTERN = Pattern.compile("([+-]?\\d*)x2(([+-]?\\d*)x)?([+-]?\\d+)?=0");
    // 1st term (required): group 1 - ([+-]?\\d*)x2
    // 2nd term (optional): group 2 - (([+-]?\\d*)x)? and group 3 - the inner subgroup ([+-]?\\d*) is for coefficient b
    // 3rd term (optional): group 4 - ([+-]?\\d+)?

    public List<Integer> extractCoefficients(String equation) throws SolverException {
        Matcher matcher = EQ_PATTERN.matcher(equation.replaceAll("\\s", ""));
        if (matcher.matches()) {
            List<Integer> r = new ArrayList<>();
            for (int i : Arrays.asList(1,3,4)) { // skip group 2.  it's a placeholder
                try {
                    r.add(Integer.parseInt(translate(matcher.group(i))));
                } catch (Exception e) {
                    throw new SolverException("Not a quadratic equation.");
                }
            }
            return r;
        } else {
            throw new SolverException("Not a quadratic equation.");
        }
    }

    public List<Double> solve(int a, int b, int c) throws SolverException {
        List<Double> result = new ArrayList<>();
        if (a == 0) {
            throw new SolverException("Not a quadratic equation.");
        }

        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            throw new SolverException("No real solution.");
        } else {
            double disc2 = Math.sqrt(discriminant);
            result.add((-b - disc2) / 2 / a);
            result.add((-b + disc2) / 2 / a);
            return result;
        }
    }
}
