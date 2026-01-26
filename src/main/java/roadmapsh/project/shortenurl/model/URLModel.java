package roadmapsh.project.shortenurl.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ShortenURLS")
public class URLModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long urlId;

    @Column(nullable = false)
    String originalURL;

    @Column(unique=true, nullable=false)
    String urlHashCode;

    @Column
    Integer timesAccessed;


    public Long getUrlId() {
        return urlId;
    }

    public void setUrlId(Long urlId) {
        this.urlId = urlId;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public String getUrlHashCode() {
        return urlHashCode;
    }

    public void setUrlHashCode(String urlHashCode) {
        this.urlHashCode = urlHashCode;
    }

    public Integer getTimesAccessed() {
        return timesAccessed;
    }

    public void setTimesAccessed(Integer timesAccessed) {
        this.timesAccessed = timesAccessed;
    }
}
