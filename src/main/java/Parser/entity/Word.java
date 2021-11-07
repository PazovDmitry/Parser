package Parser.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Getter
@Setter
public class Word {

    @Id
    @SequenceGenerator(name = "word_id_seq_generator",
            sequenceName = "word_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "word_id_seq_generator")
    private Integer id;

    private String word;

    private Integer repeat;

    public Word() {
    }

    public Word(String word, Integer repeat) {
        this.word = word;
        this.repeat = repeat;
    }
}
