package br.com.vayu.models.activities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("video")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Video extends Activity {

    @Column(nullable = false)
    private String url;

    private int durationInMinutes;

    @Type(type = "text")
    private String transcription;

}
