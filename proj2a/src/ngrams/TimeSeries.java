package ngrams;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        // TODO: Fill in this constructor.
        Set<Integer> yearSet = ts.keySet();
        for (int year : yearSet) {
            if (year <= endYear && year >= startYear) {
                this.put(year, ts.get(year));
            }
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
        Set<Integer> yearSet = this.keySet();
        return new LinkedList<>(yearSet);
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        List<Double> outputList = new LinkedList<>();
        for (int year : this.years()) {
            outputList.add(this.get(year));
        }
        return outputList;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     * <p>
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        Set<Integer> keyset = Sets.union(ts.keySet(), this.keySet());
        TimeSeries outputTimeSeries = new TimeSeries();
        for (int year :
                keyset) {
            double value = 0.0;
            if (this.get(year) != null) {
                value += this.get(year);
            }
            if (ts.get(year) != null) {
                value += ts.get(year);
            }
            outputTimeSeries.put(year, value);
        }
        return outputTimeSeries;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     * <p>
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries output = new TimeSeries();
        for (int year : this.years()) {
            if (ts.get(year) == null) {
                throw new IllegalArgumentException("The ts does not contain the element!");
            }
            output.put(year, this.get(year) / ts.get(year));
        }
        return output;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
