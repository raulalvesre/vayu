package br.com.vayu.models.activities;

import br.com.vayu.models.Section;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("explanation")
public class Explanation extends Activity {

    @Column(nullable = false)
    private String text;

    public Explanation(String code,
                       String title,
                       Section section,
                       String text) {
        super(code, title, section);

        this.text = text;
    }

    @Deprecated
    public Explanation() {
        super();
    }

}
