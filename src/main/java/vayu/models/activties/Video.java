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
                 String URL,
                 int durationInMinutes,
                 String transcription) {
        this(code, title, section, URL);

        this.durationInMinutes = durationInMinutes;
        this.transcription = transcription;
    }

    public Video(String code,
                 String title,
                 Section section,
                 String URL) {
        super(code, title, section);

        validateURL(URL);
        this.URL = URL;
    }

    public Video(String code,
                 String title,
                 boolean isActive,
                 int order,
                 Section section,
                 String URL,
                 int durationInMinutes,
                 String transcription) {
        this(code, title, isActive, order, section, URL);

        this.durationInMinutes = durationInMinutes;
        this.transcription = transcription;
    }

    public Video(String code,
                 String title,
                 boolean isActive,
                 int order,
                 Section section,
                 String URL) {
        super(code, title, isActive, order, section);

        validateURL(URL);
        this.URL = URL;
    }

    private void validateURL(String URL) {
        validateIfItIsNull(this.getClass().getSimpleName(), "URL", URL);
        validateIfItIsValidURL(this.getClass().getSimpleName(), "URL", URL);
    }

    public String getURL() {
        return URL;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public String getTranscription() {
        return transcription;
    }

}
