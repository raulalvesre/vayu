package vayu.models.activties;

import vayu.models.Section;
import vayu.models.activties.base.Activity;

public class Video extends Activity {

    private String URL;
    private int minutesOfVideo;
    private String transcription;

    public Video(String code,
                 String title,
                 boolean active,
                 int order,
                 Section section) {
        super(code, title, active, order, section);
    }
}
