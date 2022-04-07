package br.com.vayu.models.activities;

import br.com.vayu.models.Section;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("video")
public class Video extends Activity {

    @Column(nullable = false)
    private String url;

    private int durationInMinutes;

    @Type(type = "text")
    private String transcription;

    public Video(String code,
                 String title,
                 Section section,
                 String url) {
        super(code, title, section);

        this.url = url;
    }

    @Deprecated
    public Video() {
        super();
    }

}
