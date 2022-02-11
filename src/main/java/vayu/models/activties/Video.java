package vayu.models.activties;

import vayu.models.Section;
import vayu.models.activties.base.Activity;

public class Video extends Activity {

    private String URL;
    private int durationInMinutes;
    private String transcription;

    public Video(String code,
                 String title,
                 boolean active,
                 int order,
                 Section section,
                 String URL
                 ) {
        super(code, title, section);
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        if (URL == null || URL.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s URL should not be null or blank");

        this.URL = URL;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

}
