package roadmapsh.project.shortenurl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import roadmapsh.project.shortenurl.models.URLModel;

import java.util.Optional;

public interface URLRepository extends JpaRepository<URLModel, Long> {
    Optional<URLModel> findByUrlHashCode(String urlHashCode);
    Optional<URLModel> findByOriginalURL(String originalURL);
}
