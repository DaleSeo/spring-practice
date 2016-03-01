package springframework.context;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SpellChecker {

    private final Dictionary dictionary;

    public SpellChecker(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<Integer> checkDocument(List<String> document) {
        List<Integer> misspelledWords = new ArrayList<>();

        IntStream.range(0, document.size())
                .forEach(idx -> {
                    String word = document.get(idx);
                    if (!dictionary.isValidWord(word)) {
                        misspelledWords.add(idx);
                    }
                });

        return misspelledWords;
    }

}
