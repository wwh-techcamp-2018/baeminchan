package codesquad.domain;

import codesquad.support.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Promotion extends AbstractEntity {

    @Column(nullable = false)
    @NotBlank
    String description;

    @Column(nullable = false)
    @NotBlank
    String url;

    public Promotion() {
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
