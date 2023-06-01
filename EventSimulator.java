package com.te.assignment;

import java.util.*;

public class EventSimulator {
    private final Map<Object, Double> outcomeProbabilities;

    public EventSimulator(Map<Object, Double> outcomeProbabilities) {
        this.outcomeProbabilities = outcomeProbabilities;
    }

    public Object simulateEvent() {
        double randomValue = Math.random() * 100; // Generate a random value between 0 and 100
        double cumulativeProbability = 0.0;

        for (Map.Entry<Object, Double> entry : outcomeProbabilities.entrySet()) {
            cumulativeProbability += entry.getValue();
            if (randomValue < cumulativeProbability) {
                return entry.getKey();
            }
        }

        // In case the probabilities don't add up to 100, return null or throw an exception
        return null;
    }

    public List<Object> simulateOccurrences(int numOccurrences) {
        List<Object> occurrences = new ArrayList<>();

        for (int i = 0; i < numOccurrences; i++) {
            Object outcome = simulateEvent();
            occurrences.add(outcome);
        }

        return occurrences;
    }

    public static void main(String[] args) {
        // Example usage for rolling a biased six-faced dice
        Map<Object, Double> diceProbabilities = new HashMap<>();
        diceProbabilities.put(1, 10.0);
        diceProbabilities.put(2, 30.0);
        diceProbabilities.put(3, 15.0);
        diceProbabilities.put(4, 15.0);
        diceProbabilities.put(5, 30.0);
        diceProbabilities.put(6, 0.0);

        EventSimulator diceRollSimulator = new EventSimulator(diceProbabilities);
        List<Object> diceRollOccurrences = diceRollSimulator.simulateOccurrences(1000);

        // Count the occurrences of each outcome
        Map<Object, Integer> diceOutcomeCounts = new HashMap<>();
        for (Object outcome : diceRollOccurrences) {
            diceOutcomeCounts.put(outcome, diceOutcomeCounts.getOrDefault(outcome, 0) + 1);
        }

        // Print the occurrence counts for dice rolls
        for (Map.Entry<Object, Integer> entry : diceOutcomeCounts.entrySet()) {
            System.out.println("Dice Outcome: " + entry.getKey() + ", Count: " + entry.getValue());
        }

        System.out.println("--**--**--**--**--**--**--");

        // Example usage for flipping a biased coin
        Map<Object, Double> coinProbabilities = new HashMap<>();
        coinProbabilities.put("Head", 35.0);
        coinProbabilities.put("Tail", 65.0);

        EventSimulator coinFlipSimulator = new EventSimulator(coinProbabilities);
        List<Object> coinFlipOccurrences = coinFlipSimulator.simulateOccurrences(1000);

        // Count the occurrences of each outcome
        Map<Object, Integer> coinOutcomeCounts = new HashMap<>();
        for (Object outcome : coinFlipOccurrences) {
            coinOutcomeCounts.put(outcome, coinOutcomeCounts.getOrDefault(outcome, 0) + 1);
        }

        // Print the occurrence counts for coin flips
        for (Map.Entry<Object, Integer> entry : coinOutcomeCounts.entrySet()) {
            System.out.println("Coin Outcome: " + entry.getKey() + ", Count: " + entry.getValue());
        }
    }
}
