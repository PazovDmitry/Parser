package Parser.service;


import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public interface WordService {

    String parseURL() throws IOException;

    Map<String, Integer> parseString(String text);

    void saveInDataBase(Map<String, Integer> uniqueWords);

    void printUniqueWords(Map<String, Integer> uniqueWords);

}
