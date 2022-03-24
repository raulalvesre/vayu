package br.com.vayu.models.activities;

import br.com.vayu.models.Section;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static br.com.vayu.services.ValidationService.validateIfItIsValidURL;

@Entity
@DiscriminatorValue("video")
public class Video extends Activity {

    @Column(nullable = false)
    private String url;

    @Column(name = "duration_in_minutes", columnDefinition = "TINYINT")
    private int durationInMinutes;

    @Column(columnDefinition = "TEXT")
    private String transcription;

    public Video(String code,
                 String title,
                 Section section,
                 String url) {
        super(code, title, section);

        validateIfItIsValidURL("URL", url);
        this.url = url;
    }

    @Deprecated
    public Video() {
        super();
    }

}
