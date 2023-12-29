package ngrams;

import edu.princeton.cs.algs4.In;

import java.sql.Time;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    TimeSeries counts = new TimeSeries();
    HashMap<String, TimeSeries> wordsCounts = new HashMap<>();

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        In wordsFile = new In(wordsFilename);
        In countsFile = new In(countsFilename);

        // read each file into instance data structure.
        // read counts
        while (!countsFile.isEmpty()) {
            String line = countsFile.readLine();
            String[] splitLine = line.split(",");
            counts.put(Integer.valueOf(splitLine[0]), Double.valueOf(splitLine[1]));
        }

        // read words
        while (!wordsFile.isEmpty()) {
            String line = wordsFile.readLine();
            String[] splitLine = line.split("\t");
            String word = splitLine[0];
            int year = Integer.parseInt(splitLine[1]);
            TimeSeries temp = new TimeSeries();
            if (wordsCounts.get(word) != null) {
                temp = wordsCounts.get(word);
            }
            double number = Double.parseDouble(splitLine[2]);
            temp.put(year, number);
            if (wordsCounts.get(word) != null) {
                continue;
            }
            wordsCounts.put(word, temp);
        }


    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries pickWord = countHistory(word);
        TimeSeries output = new TimeSeries(pickWord, startYear, endYear);
        return output;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        if (wordsCounts.get(word) == null) {
            return new TimeSeries();
        }
        TimeSeries specificWordTS = wordsCounts.get(word);
        List<Integer> yearList = specificWordTS.years();
        TimeSeries output = new TimeSeries();
        for (int year : yearList) {
            output.put(year, specificWordTS.get(year));
        }
        return output;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        List<Integer> yearSet = counts.years();
        TimeSeries output = new TimeSeries();
        for (int year : yearSet) {
            output.put(year, counts.get(year));
        }
        return output;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries output = new TimeSeries();
        if (wordsCounts.get(word) == null) {
            return output;
        }
        TimeSeries wordHistory = wordsCounts.get(word);
        for (int year = startYear; year <= endYear; year++) {
            try {
                double frequency = wordHistory.get(year) / counts.get(year);
                output.put(year, frequency);
            } finally {
                continue;
            }
        }
        return output;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        TimeSeries output = new TimeSeries();
        if (wordsCounts.get(word) == null) {
            return output;
        }
        TimeSeries wordHistory = wordsCounts.get(word);
        List<Integer> yearList = wordHistory.years();
        for (int year : yearList) {
            double frequency = wordHistory.get(year) / counts.get(year);
            output.put(year, frequency);
        }
        return output;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries output = new TimeSeries();
        for (String word : words) {
            output = output.plus(weightHistory(word, startYear, endYear));
        }
        return output;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        return null;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
