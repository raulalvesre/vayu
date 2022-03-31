package br.com.vayu.models.activities;

import br.com.vayu.models.Section;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("video")
public class Video extends Activity {

    @Column(nullable = false)
    private String url;

    @Column(columnDefinition = "TINYINT")
    private int durationInMinutes;

    @Column(columnDefinition = "TEXT")
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
