package de.fearlessTobi.caesartools

import java.util.*

internal object ProgramLogic {

    private val shiftedStringsByAverageDifference = TreeMap<Double, String>()
    private var elementIndex: Int = 0

    private val characterFrequencyWeights: Array<Double>
        get() {
            return arrayOf(
                    6.51
                    , 1.89
                    , 3.06
                    , 5.08
                    , 17.40
                    , 1.66
                    , 3.01
                    , 4.76
                    , 7.55
                    , 0.27
                    , 1.21
                    , 3.44
                    , 2.53
                    , 9.78
                    , 2.51
                    , 0.79
                    , 0.02
                    , 7.00
                    , 7.27
                    , 6.15
                    , 4.35
                    , 0.67
                    , 1.89
                    , 0.03
                    , 0.04
                    , 1.13
            )
        }

    fun decryptText(encryptedText: String): ValuePair {
        shiftedStringsByAverageDifference.clear()
        elementIndex = 0

        val shiftedStrings = HashMap<Int, String>()
        val characterFrequencyWeights = characterFrequencyWeights

        for (i in 0..25) {
            // Shift text by "i"
            shiftedStrings[i] = encryptText(encryptedText, i)

            val characterFrequency = IntArray(26)
            var total = 0
            val differences = ArrayList<Double>()

            // Count all alphabetic letters in the String
            for (alphabet in 'A'..'Z') {
                val countedLetter = countLetter(shiftedStrings.getValue(i), alphabet)
                characterFrequency[alphabet.toInt() - 65] = countedLetter

                total += countedLetter
            }

            // Check if there is no alphabetical letter in the String
            if (total == 0)
                return ValuePair(encryptedText, 0.0, true)

            for (f in 0..25) {
                if (characterFrequency[f] != 0) {
                    val characterFrequencyPercent = characterFrequency[f] / total.toDouble() * 100

                    // Calculate difference from character frequency weights for
                    // each character which isn't 0
                    val difference: Double = if (characterFrequencyWeights[f] > characterFrequencyPercent) {
                        characterFrequencyWeights[f] - characterFrequencyPercent
                    } else {
                        characterFrequencyPercent - characterFrequencyWeights[f]
                    }

                    differences.add(difference)
                }
            }

            // Add all differences to a totalDifference
            val totalDifference = differences.sum()

            var averageDifference = totalDifference / differences.size

            // If object key already exists in TreeMap
            while (shiftedStringsByAverageDifference.containsKey(averageDifference))
                averageDifference += 0.00001

            shiftedStringsByAverageDifference[averageDifference] = shiftedStrings.getValue(i)
        }

        // Get String with least average difference
        val leastAverageDifference = shiftedStringsByAverageDifference.keys.toTypedArray()[elementIndex]
        val leastAverageDifferenceRounded = Math.round(leastAverageDifference * 100) / 100.0

        val shiftedStringWithLeastAverageDifference = shiftedStringsByAverageDifference.getValue(leastAverageDifference)

        return ValuePair(shiftedStringWithLeastAverageDifference, leastAverageDifferenceRounded, false)
    }

    fun encryptText(decryptedText: String, shiftBy: Int): String {
        val encryptedText = StringBuilder()
        val shiftBy = normalizeShiftBy(shiftBy)

        for (i in 0 until decryptedText.length) {
            var charToShift = decryptedText[i]

            if (charToShift in 'a'..'z' || charToShift in 'A'..'Z') {
                charToShift += shiftBy

                if (Character.isUpperCase(decryptedText[i]) && charToShift.toInt() > 90)
                    charToShift -= 26
                else if (Character.isLowerCase(decryptedText[i]) && charToShift.toInt() > 122)
                    charToShift -= 26
            }
            encryptedText.append(charToShift)
        }
        return encryptedText.toString()
    }

    fun goForward(amount: Int): ValuePair? {
        elementIndex += amount

        if (shiftedStringsByAverageDifference.size == 0)
            return null

        if (elementIndex > 25)
            elementIndex -= 26

        if (elementIndex < 0)
            elementIndex += 26

        val leastAverageDifference = shiftedStringsByAverageDifference.keys.toTypedArray()[elementIndex]
        val leastAverageDifferenceRounded = Math.round(leastAverageDifference * 100) / 100.0

        val shiftedStringWithLeastAverageDifference = shiftedStringsByAverageDifference.getValue(leastAverageDifference)

        return ValuePair(shiftedStringWithLeastAverageDifference, leastAverageDifferenceRounded, false)
    }

    private fun normalizeShiftBy(shiftBy: Int): Int {
        var shiftBy = shiftBy
        while (shiftBy > 26) {
            shiftBy -= 26
        }

        while (shiftBy < 0) {
            shiftBy += 26
        }

        return shiftBy
    }

    private fun countLetter(str: String, letter: Char): Int {
        val str = str.toLowerCase()
        val letter = Character.toLowerCase(letter)
        var count = 0

        for (i in 0 until str.length) {
            val currentLetter = str[i]
            if (currentLetter == letter)
                count++
        }

        return count
    }

}