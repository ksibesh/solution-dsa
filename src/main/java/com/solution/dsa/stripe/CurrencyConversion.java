package com.solution.dsa.stripe;

import com.solution.dsa.stripe.model.ConversionQuery;
import com.solution.dsa.stripe.model.ConversionRate;

import java.util.*;

public class CurrencyConversion {

    public double queryConversion(String conversionRateStr, String queryStr) {
        List<ConversionRate> conversionRates = decodeConversionRate(conversionRateStr);
        Map<String, List<ConversionRate>> conversionGraph = buildGraph(conversionRates);
        ConversionQuery query = decodeQuery(queryStr);
        Optional<Double> rate = logic(conversionGraph, query.getFrom(), query.getTo(), new HashSet<>());
        return rate.orElse(0.00);
    }

    private Optional<Double> logic(Map<String, List<ConversionRate>> graph, String from, String to, Set<String> visited) {
        if (from.equals(to)) return Optional.of(1.00);
        if (!graph.containsKey(from) || visited.contains(from)) return Optional.empty();

        visited.add(from);
        for (ConversionRate edge : graph.get(from)) {
            Optional<Double> result = logic(graph, edge.getTo(), to, visited);
            if (result.isPresent()) return Optional.of(result.get() * edge.getRate());
        }
        return Optional.empty();
    }

    private List<ConversionRate> decodeConversionRate(String str) {
        List<ConversionRate> conversionRates = new ArrayList<>();
        for (String conversionRate : str.split(",")) {
            String[] conversionRateParam = conversionRate.trim().split(":");
            conversionRates.add(new ConversionRate(conversionRateParam[0].trim(),
                    conversionRateParam[1].trim(),
                    Double.parseDouble(conversionRateParam[2].trim())));
        }
        return conversionRates;
    }

    private ConversionQuery decodeQuery(String query) {
        String[] queryParam = query.split(":");
        return new ConversionQuery(queryParam[0], queryParam[1]);
    }

    private Map<String, List<ConversionRate>> buildGraph(List<ConversionRate> conversionRates) {
        Map<String, List<ConversionRate>> graph = new HashMap<>();
        Map<String, Set<String>> memory = new HashMap<>();
        for (ConversionRate conversionRate : conversionRates) {

            if (memory.get(conversionRate.getFrom()) != null
                    && memory.get(conversionRate.getFrom()).contains(conversionRate.getTo())) {
                throw new IllegalArgumentException(String.format("Duplicate Entry Found for %s->%s",
                        conversionRate.getFrom(), conversionRate.getTo()));
            }

            graph.putIfAbsent(conversionRate.getFrom(), new ArrayList<>());
            graph.get(conversionRate.getFrom()).add(conversionRate);

            memory.putIfAbsent(conversionRate.getFrom(), new HashSet<>());
            memory.get(conversionRate.getFrom()).add(conversionRate.getTo());
        }
        return graph;
    }

    public static void main(String[] args) {
        CurrencyConversion cc = new CurrencyConversion();
        double epsilon = 0.000001; // Tolerance for floating point comparison

        // Scenario 1: Direct Conversion
        double res1 = cc.queryConversion("USD:EUR:0.92", "USD:EUR");
        System.out.println("Test 1 (Direct): " + (Math.abs(res1 - 0.92) < epsilon ? "PASSED" : "FAILED (" + res1 + ")"));

        // Scenario 2: Transitive Conversion
        // USD -> EUR (0.9) * EUR -> GBP (0.8) = 0.72
        double res2 = cc.queryConversion("USD:EUR:0.9, EUR:GBP:0.8", "USD:GBP");
        System.out.println("Test 2 (Transitive): " + (Math.abs(res2 - 0.72) < epsilon ? "PASSED" : "FAILED (" + res2 + ")"));

        // Scenario 3: No Path Exists
        double res3 = cc.queryConversion("USD:EUR:0.9, JPY:INR:0.5", "USD:INR");
        System.out.println("Test 3 (No Path): " + (res3 == 0.0 ? "PASSED" : "FAILED"));

        // Scenario 4: Circular Reference Safety
        // Should return 0.0 gracefully instead of crashing
        double res4 = cc.queryConversion("USD:EUR:0.9, EUR:USD:1.1", "USD:JPY");
        System.out.println("Test 4 (Cycle): " + (res4 == 0.0 ? "PASSED" : "FAILED"));

        // Scenario 5: Duplicate Entry Check
        try {
            cc.queryConversion("USD:EUR:0.9, USD:EUR:0.95", "USD:EUR");
            System.out.println("Test 5 (Duplicate): FAILED (Should have thrown exception)");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 5 (Duplicate): PASSED");
        }

        // Scenario 6: Same Currency
        double res6 = cc.queryConversion("USD:EUR:0.92", "USD:USD");
        System.out.println("Test 6 (Self): " + (res6 == 1.0 ? "PASSED" : "FAILED (" + res6 + ")"));
    }

}
