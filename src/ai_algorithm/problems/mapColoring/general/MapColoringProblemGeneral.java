package ai_algorithm.problems.mapColoring.general;

import ai_algorithm.problems.mapColoring.AbstractMapColoringProblem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapColoringProblemGeneral extends AbstractMapColoringProblem{

    public MapColoringProblemGeneral(){
        variables = Arrays.asList("A", "B", "C", "D", "E", "F", "G");
        neighbors = Map.of(
                "A", List.of("B", "C"), // Constraint from WA
                "B", List.of("A", "C", "D"), // Constraint from NT
                "C", List.of("A", "B", "D", "E", "F"), // Constraint from SA
                "D", List.of("C", "B", "E"), // Constraint from Q
                "E", List.of("C", "D", "F"), // Constraint from NSW
                "F", List.of("E", "C"), // Constraint from V
                "G", List.of() // Constraint from T
        );

        domain = new HashMap<>();
        for (String variable : variables) {
            domain.put(variable, Arrays.asList("R", "G", "B"));
        }
        fillQueue();

        variableToCircleMap = new HashMap<>();
        variableTextMap = new HashMap<>();
    }

}