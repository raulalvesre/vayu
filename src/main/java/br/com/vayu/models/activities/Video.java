package br.com.vayu.models.activities;

import br.com.vayu.services.ValidationService;
import br.com.vayu.models.Section;

public class Video extends Activity {

    private final String url;
    private int durationInMinutes;
    private String transcription;

    public Video(String code,
                 String title,
                 Section section,
                 String url) {
        super(code, title, section);

        ValidationService.validateIfItIsValidURL("URL", url);
        this.url = url;
    }

}
