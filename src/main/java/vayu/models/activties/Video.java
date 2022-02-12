package vayu.models.activties;

import vayu.models.Section;
import vayu.models.activties.base.Activity;

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

        if (URL == null || URL.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s URL should not be null or blank");

        this.URL = URL;
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
