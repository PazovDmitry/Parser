/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Parser;

import Parser.service.WordService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Map;

@SpringBootApplication
public class ParserApplication {

    public static WordService wordService;
    public static String text;
    public static Map<String, Integer> uniqueWords;

    public ParserApplication (WordService wordService){
        ParserApplication.wordService = wordService;
    }


    public static void main(String[] args) throws IOException {

        SpringApplication.run(ParserApplication.class);


        text = wordService.parseURL();
        uniqueWords = wordService.parseString(text);
        wordService.printUniqueWords(uniqueWords);
        wordService.saveInDataBase(uniqueWords);

    }
}
