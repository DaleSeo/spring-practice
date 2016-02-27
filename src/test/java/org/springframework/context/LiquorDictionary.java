package org.springframework.context;

import java.util.Arrays;
import java.util.List;

public class LiquorDictionary implements Dictionary {

    private final List<String> validWords = Arrays.asList("Beer", "Wine", "Whiskey");

    @Override
    public boolean isValidWord(String word) {
        return validWords.contains(word);
    }

}
