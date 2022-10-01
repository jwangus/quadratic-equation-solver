package code.solver;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SolverService {
    public SolverResponse solve(String equation) {
        try {
            List<Integer> coefficients = extractCoefficients(equation);
            List<Double> results = solve(coefficients.get(0), coefficients.get(1), coefficients.get(2));
            return new SolverResponse(results, equation, "ok");
        } catch (SolverException e) {
            return new SolverResponse(new ArrayList<>(), equation, e.getMessage());
        }
    }

    public List<Integer> extractCoefficients(@NotNull String equation) throws SolverException {
        var eqPattern = Pattern.compile("(?<a>[+-]?\\d*|\\d+)x2(?<b>[+-]?\\d*)x(?<c>[+-]?\\d+|\\d*)=0\\s*");
        Matcher matcher = eqPattern.matcher(equation.replace(" ", ""));
        if (matcher.matches()) {
            List<Integer> r = new ArrayList<>();
            var coeff = matcher.group("a");
            if (coeff.equals("+") || coeff.isEmpty()) {
                coeff = "1";
            } else if (coeff.equals("-")) {
                coeff = "-1";
            }
            try {
                r.add(Integer.parseInt(coeff));
            } catch (Exception e) {
                throw new SolverException("Not a quadratic equation.");
            }

            coeff = matcher.group("b");
            if (coeff.equals("+")) {
                coeff = "1";
            } else if (coeff.equals("-")) {
                coeff = "-1";
            }
            try {
                r.add(Integer.parseInt(coeff));
            } catch (Exception e) {
                throw new SolverException("Not a quadratic equation.");
            }

            coeff = matcher.group("c");
            try {
                r.add(Integer.parseInt(coeff));
            } catch (Exception e) {
                throw new SolverException("Not a quadratic equation.");
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

        double discTemp = b * b - 4 * a * c;
        if (discTemp < 0) {
            throw new SolverException("No real solution.");
        } else {
            double disc = Math.sqrt(discTemp);
            result.add((-b - disc) / 2 / a);
            result.add((-b + disc) / 2 / a);
            return result;
        }
    }
}
