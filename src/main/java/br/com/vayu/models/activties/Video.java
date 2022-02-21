package br.com.vayu.models.activties;

import br.com.vayu.models.Section;

import static br.com.vayu.services.ValidationService.*;

public class Video extends Activity {

    private final String URL;
    private int durationInMinutes;
    private String transcription;

    public Video(String code,
                 String title,
                 Section section,
                 String URL) {
        super(code, title, section);

        validateIfItIsValidURL("URL", URL);
        this.URL = URL;
    }

}
