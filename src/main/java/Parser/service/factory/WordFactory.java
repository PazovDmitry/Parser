package Parser.service.factory;

import Parser.entity.Word;
import org.springframework.stereotype.Component;

@Component
public class WordFactory {

    public Word build(String word, Integer repeat) {
        return new Word(word, repeat);
    }
}
