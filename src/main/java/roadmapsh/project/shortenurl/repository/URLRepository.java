package roadmapsh.project.shortenurl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import roadmapsh.project.shortenurl.DTO.URLDTO;
import roadmapsh.project.shortenurl.model.URLModel;

import java.util.List;
import java.util.Optional;

public interface URLRepository extends JpaRepository<URLModel, Long> {
    Optional<URLModel> findByUrlHashCode(String urlHashCode);
    Optional<URLModel> findByOriginalURL(String originalURL);
}
