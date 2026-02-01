package roadmapsh.project.shortenurl.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

    @CreationTimestamp
    @Column (updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;


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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime localDateTime) {
        this.createdAt = localDateTime;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime localDateTime) {
        this.updatedAt = localDateTime;
    }
}
