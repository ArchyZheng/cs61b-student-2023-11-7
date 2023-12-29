package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        NGramMap ngm = new NGramMap("./data/ngrams/top_14377_words.csv",
                "./data/ngrams/total_counts.csv");

        StringBuilder SB = new StringBuilder();
        int count = 0;
        for (String word : words) {
            TimeSeries weightedHistory = ngm.weightHistory(word, startYear, endYear);
            SB.append(word + ": ");
            StringBuilder temp = new StringBuilder("{");
            for (int year=startYear; year <= endYear; year++) {
                temp.append(String.valueOf(year) + "=" + String.valueOf(weightedHistory.get(year)));
                if (year != endYear) {
                    temp.append(", ");
                }
            }
            temp.append("}");
            count += 1;
            if (count != words.size()) {
                temp.append("\n");
            }
            String tempString = temp.toString();
            SB.append(tempString);
        }
        return SB.toString();
    }
}
