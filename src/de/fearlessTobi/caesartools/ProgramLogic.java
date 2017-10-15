package de.fearlessTobi.caesartools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class ProgramLogic {

    public static TreeMap<Double, String> shiftedStringsByAverageDifference = new TreeMap<>();
    public static int elementIndex;

    public static ValuePair decryptText(String encryptedText) {

        shiftedStringsByAverageDifference.clear();
        elementIndex = 0;

        HashMap<Integer, String> shiftedStrings = new HashMap<>();
        double[] characterFrequencyWeights = getCharacterFrequencyWeights();

        for (int i = 0; i < 26; i++) {

            // Shift text by "i"
            shiftedStrings.put(i, encryptText(encryptedText, i));

            int[] characterFrequency = new int[26];
            int total = 0;
            ArrayList<Double> differences = new ArrayList<>();

            // Count all alphabetic letters in the String
            for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
                int countedLetter = (countLetter(shiftedStrings.get(i), alphabet));
                characterFrequency[alphabet - 65] = countedLetter;

                total += countedLetter;
            }

            // Check if there is no alphabetical letter in the String
            if (total == 0)
                return new ValuePair(encryptedText, 0.0, true);

                for (int f = 0; f < 26; f++) {

                    if (characterFrequency[f] != 0) {

                        double characterFrequencyPercent = characterFrequency[f] / (double) total * 100;
                        double difference;

                        // Calculate difference from character frequency weights for
                        // each character which isnt't 0
                        if (characterFrequencyWeights[f] > characterFrequencyPercent) {
                            difference = characterFrequencyWeights[f] - characterFrequencyPercent;
                        } else {
                            difference = characterFrequencyPercent - characterFrequencyWeights[f];
                        }

                        differences.add(difference);

                    }

                }

                double totalDifference = 0;

                // Add all differences to a totalDifference
                for (double dif : differences) {
                    totalDifference += dif;
                }

                double averageDifference = totalDifference / differences.size();

                // If object key already exists in TreeMap
                while (shiftedStringsByAverageDifference.containsKey(averageDifference))
                    averageDifference += 0.00001;

                shiftedStringsByAverageDifference.put(averageDifference, shiftedStrings.get(i));

            }

            // Get String with least average difference
            double leastAverageDifference = (double) shiftedStringsByAverageDifference.keySet().toArray()[elementIndex];
            double leastAverageDifferenceRounded = Math.round(leastAverageDifference * 100) / 100.0;

            String shiftedStringWithLeastAverageDifference = shiftedStringsByAverageDifference.get(leastAverageDifference);

            return new ValuePair(shiftedStringWithLeastAverageDifference, leastAverageDifferenceRounded, false);

        }

    public static String encryptText(String decryptedText, int shiftBy) {

        StringBuilder encryptedText = new StringBuilder();

        shiftBy = normalizeShiftBy(shiftBy);

        for (int i = 0; i < decryptedText.length(); i++) {

            char charToShift = decryptedText.charAt(i);

            if ((charToShift >= 'a' && charToShift <= 'z') || (charToShift >= 'A' && charToShift <= 'Z')) {

                charToShift += shiftBy;

                if (Character.isUpperCase(decryptedText.charAt(i)) && charToShift > 90)
                    charToShift -= 26;
                else if (Character.isLowerCase(decryptedText.charAt(i)) && charToShift > 122)
                    charToShift -= 26;

            }

            encryptedText.append(charToShift);

        }

        return encryptedText.toString();

    }

    public static ValuePair goForward(int amount) {

        elementIndex += amount;

        if (shiftedStringsByAverageDifference == null || shiftedStringsByAverageDifference.size() == 0)
            return null;

        if (elementIndex > 25)
            elementIndex -= 26;

        if (elementIndex < 0)
            elementIndex += 26;

        double leastAverageDifference = (double) shiftedStringsByAverageDifference.keySet().toArray()[elementIndex];
        double leastAverageDifferenceRounded = Math.round(leastAverageDifference * 100) / 100.0;

        String shiftedStringWithLeastAverageDifference = shiftedStringsByAverageDifference.get(leastAverageDifference);

        return new ValuePair(shiftedStringWithLeastAverageDifference, leastAverageDifferenceRounded, false);

    }

    public static int normalizeShiftBy(int shiftBy) {

        while (shiftBy > 26) {
            shiftBy -= 26;
        }

        while (shiftBy < 0) {
            shiftBy += 26;
        }

        return shiftBy;

    }

    public static int countLetter(String str, char letter) {
        str = str.toLowerCase();
        letter = Character.toLowerCase(letter);
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            char currentLetter = str.charAt(i);
            if (currentLetter == letter)
                count++;
        }

        return count;
    }

    public static double[] getCharacterFrequencyWeights() {

        double[] characterFrequencyWeights = new double[26];

        characterFrequencyWeights[0] = 6.51;
        characterFrequencyWeights[1] = 1.89;
        characterFrequencyWeights[2] = 3.06;
        characterFrequencyWeights[3] = 5.08;
        characterFrequencyWeights[4] = 17.40;
        characterFrequencyWeights[5] = 1.66;
        characterFrequencyWeights[6] = 3.01;
        characterFrequencyWeights[7] = 4.76;
        characterFrequencyWeights[8] = 7.55;
        characterFrequencyWeights[9] = 0.27;
        characterFrequencyWeights[10] = 1.21;
        characterFrequencyWeights[11] = 3.44;
        characterFrequencyWeights[12] = 2.53;
        characterFrequencyWeights[13] = 9.78;
        characterFrequencyWeights[14] = 2.51;
        characterFrequencyWeights[15] = 0.79;
        characterFrequencyWeights[16] = 0.02;
        characterFrequencyWeights[17] = 7.00;
        characterFrequencyWeights[18] = 7.27;
        characterFrequencyWeights[19] = 6.15;
        characterFrequencyWeights[20] = 4.35;
        characterFrequencyWeights[21] = 0.67;
        characterFrequencyWeights[22] = 1.89;
        characterFrequencyWeights[23] = 0.03;
        characterFrequencyWeights[24] = 0.04;
        characterFrequencyWeights[25] = 1.13;

        return characterFrequencyWeights;

    }

}