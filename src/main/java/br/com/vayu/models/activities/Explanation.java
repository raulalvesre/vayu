package br.com.vayu.models.activities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("explanation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Explanation extends Activity {

    @Column(nullable = false)
    private String text;

}
