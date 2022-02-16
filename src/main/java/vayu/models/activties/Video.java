package vayu.models.activties;

import vayu.models.Section;
import vayu.models.activties.base.Activity;

import static vayu.services.ValidationService.*;

public class Video extends Activity {

    private final String URL;
    private int durationInMinutes;
    private String transcription;

    public Video(String code,
                 String title,
                 Section section,
                 String URL) {
        super(code, title, section);

        validateIfItIsValidURL(this.getClass().getSimpleName(), "URL", URL);
        this.URL = URL;
    }

}
