package Parser.service.impl;


import Parser.entity.Word;
import Parser.repository.WordRepository;
import Parser.service.WordService;
import Parser.service.factory.WordFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

@Component
@Service
public class JpaWordService implements WordService {

    private final WordRepository wordRepository;
    private final WordFactory wordFactory;


    public JpaWordService(WordRepository wordRepository,
                          WordFactory wordFactory) {
        this.wordRepository = wordRepository;
        this.wordFactory = wordFactory;
    }

    @Override
    public String parseURL() throws IOException{
        System.out.println("Enter the URL:");
        BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));
        String link = null;
        while (link == null) {
            try {
                URL url = new URL(reader.readLine());
                URLConnection connection = url.openConnection();
                connection.connect();
                link = url.toString();
                reader.close();
            } catch (MalformedURLException e) {
                System.out.println("URL is invalid");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Document doc = Jsoup.connect(link).get();
        return doc.body().text();
    }

    @Override
    public Map<String, Integer> parseString(String text) {
        Map<String, Integer> uniqueWords = new HashMap<>();
        String[] mass = text.split("[^A-Za-z\\p{L}]+");
        for (int i=0; i< mass.length; i++){
            int count = 0;
            for (int j = 0; j < mass.length; j++) {
                if (mass[i].equalsIgnoreCase(mass[j]))
                    count++;
            }
            uniqueWords.put(mass[i].toUpperCase(), count);
        }
        return uniqueWords;
    }

    @Override
    public void printUniqueWords(Map<String, Integer> uniqueWords) {
        for (String key : uniqueWords.keySet()) {
            Integer value = uniqueWords.get(key);
            System.out.println(key + " - " + value);
        }
    }

    @Override
    public void saveInDataBase (Map < String, Integer > uniqueWords){
        for (String key : uniqueWords.keySet()) {
            Integer value = uniqueWords.get(key);
            Word word = wordFactory.build(key, value);
            wordRepository.saveAndFlush(word);
            }
        }
    }