package br.com.vayu.models.activities;

import br.com.vayu.models.Section;

import static br.com.vayu.services.ValidationService.validateIfItIsValidURL;

public class Video extends Activity {

    private final String url;
    private int durationInMinutes;
    private String transcription;

    public Video(String code,
                 String title,
                 Section section,
                 String url) {
        super(code, title, section);

        validateIfItIsValidURL("URL", url);
        this.url = url;
    }

}
