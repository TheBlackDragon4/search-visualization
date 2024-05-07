package ai_algorithm.specific_algorithm_logic.csp.csp_examples.tests;

import java.util.*;

/**
 * Short implementation of the AC3 algorithm for CSPs.
 *
 * @author Alexander
 */

public class AC3AlgorithmShortImplementation_test {

    // Declare the variables, constraints, domains, assignments and arcs
    static List<String> variables = Arrays.asList("WA", "NT", "SA", "Q", "NSW", "V", "T");
    static List<List<String>> constraints = Arrays.asList(
            Arrays.asList("NT", "SA"),
            Arrays.asList("WA", "SA", "Q"),
            Arrays.asList("WA", "NT", "Q"),
            Arrays.asList("SA", "NT", "NSW"),
            Arrays.asList("SA", "Q", "V"),
            Arrays.asList("NSW", "SA"),
            Arrays.asList("T"),
            Collections.emptyList()
    );

    static List<List<String>> d = Arrays.asList(
            new ArrayList<>(Arrays.asList("red", "green", "blue")),
            new ArrayList<>(Arrays.asList("red", "green", "blue")),
            new ArrayList<>(Arrays.asList("red", "green", "blue")),
            new ArrayList<>(Arrays.asList("red", "green", "blue")),
            new ArrayList<>(Arrays.asList("red", "green", "blue")),
            new ArrayList<>(Arrays.asList("red", "green", "blue")),
            new ArrayList<>(Arrays.asList("red", "green", "blue"))
    );

    static Map<String, String> assignments = new HashMap<>();
    static List<List<String>> arcs = new ArrayList<>();

    // Main method to run the AC3 algorithm
    public static void main(String[] args) {
        AC3();
    }

    // AC3 algorithm implementation
    public static void AC3() {
        fillQueue(); // Fill the queue with the initial arcs
        System.out.println("\nVariable sequence: " + variables + "\n");
        System.out.println("Initial arcs: " + arcs);
        while (!arcs.isEmpty()) { // While the queue is not empty
            List<String> arcVars = arcs.remove(0); // Remove the first arc from the queue
            System.out.println("Checking arc: " + arcVars);
            if (revise(arcVars.get(0), arcVars.get(1))) { // Revise the domain of the arc
                System.out.println("Revised domain: " + d);
                int dIIndex = variables.indexOf(arcVars.get(0)); // Get the index of the variable in the domain
                if (d.get(dIIndex).isEmpty()) { // If the domain is empty
                    System.out.println("No Solution");
                    return;
                }
                List<String> neighbors = new ArrayList<>(constraints.get(dIIndex)); // Get the neighbors of the variable
                neighbors.remove(arcVars.get(1)); // Remove the second variable from the neighbors
                for (String xk : neighbors) { // For each neighbor
                    arcs.add(Arrays.asList(xk, arcVars.get(0))); // Add the neighbor and the first variable to the queue
                }
                System.out.println("Updated arcs: " + arcs);
            }
            System.out.println("\nBetween assignments: " + assignments + "\n");
        }
        System.out.println("Final assignments: " + assignments);
    }

    /**
     * Fill the queue with the initial arcs
     */
    public static void fillQueue() {
        for (String var : variables) { // For each variable
            int varIndex = variables.indexOf(var); // Get the index of the variable
            for (String arc : constraints.get(varIndex)) { // For each constraint of the variable
                arcs.add(Arrays.asList(var, arc)); // Add the variable and the arc to the queue
            }
        }
    }

    /**
     * Revise the domain of the variables
     * @param Xi The first variable
     * @param Xj The second variable
     * @return True if the domain was revised, false otherwise
     */
    public static boolean revise(String Xi, String Xj) {
        boolean revised = false; // Initialize the revised flag
        int Iindex = variables.indexOf(Xi); // Get the index of the first variable
        System.out.println("Revising domain for " + Xi + " and " + Xj);
        for (String x : new ArrayList<>(d.get(Iindex))) {
            if (!constraints.get(Iindex).contains(Xj)) {
                break;
            }
            if (!assignments.containsKey(Xj)) {
                continue;
            }
            if (assignments.get(Xj).equals(x)) {
                d.get(Iindex).remove(x); // Remove the value from the domain
                revised = true;
                System.out.println("Removed " + x + " from domain of " + Xi);
            }
        }
        if (!revised && !d.get(Iindex).isEmpty()) { // If the domain was not revised
            assignments.put(Xi, d.get(Iindex).get(0)); // Assign the first value from the domain
            System.out.println("Assigned " + d.get(Iindex).get(0) + " to " + Xi);
        }
        return revised;
    }

}


/*
 * Copyright (c) 2024 Alexander Ultsch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */