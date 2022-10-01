# Code Challenge TD
## Requirements
- It is necessary to implement a component for solving quadratic equations.
- In addition to a standard API the solution should provide an "integration" text-based interface, e.g. used in a HTTP-based service or by a component implementing file-based exchange.
- That interface should accept a string as input and produce a string. The format of the input and output is defined by the component's developer. The input and output format should be machine-parsable.
- The solution should come with corresponding test coverage.
- Please submit the solution via a github repo
- The solution should be built in Java 8+ using Spring Boot. The reviewers will look for:
  - Best coding practices (unit tests…)
  - Exception handling / input validation / choice of data types
  - Patterns, optimal use of Spring Boot construct and use of relevant third party libraries (i.e. use of Jackson for JSON output)

## Analysis
Implement a solution for solving quadratic equations.

Assuming Quadratic Equation is in Standard Form:

    ax2 + bx + c = 0

Quadratic Formula:

    answer1 = (-b + sqrt(b^2-4ac) )/2a
    answer2 = (-b - sqrt(b^2-4ac) )/2a

When the discriminant `(b^2 − 4ac)` is:
* positive, there are 2 real solutions
* zero, there is one real solution
* negative, there are 2 complex solutions.

## How to use the microservice?

1. Use the command to start the server: `./gradlew bootRun`
2. Copy and paste the below link to a browser address bar.
`http://localhost:8080/api/v1/quadratic-equation-solver/5x2%2B6x%2B1%3D0`
3. You'll see the following JSON output:
`{"answers":[-1.0,-0.2],"equation":"5x2+6x+1=0","description":"Solved."}`

