package org.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Data
@NoArgsConstructor
@Embeddable
public class Avatar implements Serializable {
    private byte[] image;

    public Avatar(byte[] image) {
        this.image = image;
    }
}
