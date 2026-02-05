package com.solution.dsa.stripe.shippingCalculator;

import com.solution.dsa.stripe.shippingCalculator.model.CostRange;
import com.solution.dsa.stripe.shippingCalculator.model.CostRangeType;
import com.solution.dsa.stripe.shippingCalculator.model.ShippingLineItem;
import com.solution.dsa.stripe.shippingCalculator.model.ShippingMatrix;

import java.util.*;

public class ShippingCalculator {

    public double calculateShippingCost(Map<String, List<ShippingMatrix>> matrix, Map<String, List<ShippingLineItem>> order) {

        double totalCost = 0.0;
        for (String country : order.keySet()) {
            if (matrix.get(country) == null)
                throw new IllegalArgumentException("Country not present in matrix, cannot fulfill the order");

            Map<String, ShippingMatrix> productLevelMatrix = new HashMap<>();
            for (ShippingMatrix matrixItem : matrix.get(country)) {
                productLevelMatrix.put(matrixItem.getProductCode(), matrixItem);
            }

            for (ShippingLineItem lineItem : order.get(country)) {
                ShippingMatrix matrixItem = productLevelMatrix.get(lineItem.getProductCode());
                int remainingQty = lineItem.getQty();

                for (CostRange costRange : matrixItem.getCostRanges()) {
                    if (remainingQty <= 0) break;

                    int tierCapacity;
                    if (costRange.getEnd() == -1) {
                        tierCapacity = remainingQty;
                    } else {
                        tierCapacity = costRange.getEnd() - costRange.getStart() + 1;
                    }

                    int qtyInThisTier = Math.min(remainingQty, tierCapacity);
                    totalCost += calculateCost(qtyInThisTier, costRange.getType(), costRange.getCost());
                    remainingQty -= qtyInThisTier;
                }
            }

        }
        return totalCost;
    }

    private double calculateCost(int qty, CostRangeType type, double cost) {
        if (CostRangeType.FIXED.equals(type)) {
            return cost;
        } else {
            return cost * qty;
        }
    }

    public static void main(String[] args) {
        ShippingCalculator calculator = new ShippingCalculator();

        // 1. Setup Matrix
        Map<String, List<ShippingMatrix>> matrix = new HashMap<>();

        // US Setup
        matrix.put("US", List.of(
                new ShippingMatrix("laptop", List.of(
                        new CostRange(1, 2, 1000.0, CostRangeType.FIXED),     // 1-2: $1000 Total
                        new CostRange(3, -1, 500.0, CostRangeType.INCREMENTAL) // 3+: $500 each
                )),
                new ShippingMatrix("keyboard", List.of(
                        new CostRange(1, -1, 100.0, CostRangeType.INCREMENTAL) // All: $100 each
                ))
        ));

        // CA (Canada) Setup - Different Rules
        matrix.put("CA", List.of(
                new ShippingMatrix("laptop", List.of(
                        new CostRange(1, 1, 1200.0, CostRangeType.FIXED),     // 1st laptop: $1200
                        new CostRange(2, -1, 1100.0, CostRangeType.FIXED)     // 2nd laptop onwards: $1100 Flat fee
                ))
        ));

        // 2. Define a Multi-Country Order
        Map<String, List<ShippingLineItem>> multiOrder = new HashMap<>();

        // US: 4 Laptops and 2 Keyboards
        // Laptop Cost: 1000 (Fixed for 1-2) + (2 * 500) = 2000
        // Keyboard Cost: 2 * 100 = 200
        // US Total: 2200
        multiOrder.put("US", List.of(
                new ShippingLineItem("laptop", 4),
                new ShippingLineItem("keyboard", 2)
        ));

        // CA: 2 Laptops
        // Laptop Cost: 1200 (Fixed for 1) + 1100 (Fixed for 2) = 2300
        // CA Total: 2300
        multiOrder.put("CA", List.of(
                new ShippingLineItem("laptop", 2)
        ));

        // 3. Run Calculation
        try {
            double total = calculator.calculateShippingCost(matrix, multiOrder);
            System.out.println("Combined Multi-Country Total: " + total);
            System.out.println("Expected: 4500.0 (US: 2200 + CA: 2300)");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 4. Test Error Case: Order for a country not in matrix
        Map<String, List<ShippingLineItem>> invalidOrder = new HashMap<>();
        invalidOrder.put("UK", List.of(new ShippingLineItem("laptop", 1)));

        System.out.println("\nTesting Invalid Country (UK):");
        try {
            calculator.calculateShippingCost(matrix, invalidOrder);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}
