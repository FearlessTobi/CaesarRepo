package de.fearlessTobi.caesartools;

public class ValuePair {

    private final String shiftedStringWithLeastAverageDifference;
    private final double leastAverageDifferenceRounded;
    private final boolean isTotalZero;

    public ValuePair(String shiftedStringWithLeastAverageDifference, double leastAverageDifferenceRounded, boolean isTotalZero) {

        this.shiftedStringWithLeastAverageDifference = shiftedStringWithLeastAverageDifference;
        this.leastAverageDifferenceRounded = leastAverageDifferenceRounded;
        this.isTotalZero = isTotalZero;

    }

    public String getShiftedStringWithLeastAverageDifference() {
        return shiftedStringWithLeastAverageDifference;
    }

    public double getLeastAverageDifferenceRounded() {
        return leastAverageDifferenceRounded;
    }

    public boolean getIsTotalZero() {
        return isTotalZero;
    }

}
