package roadmapsh.project.shortenurl.DTO.url;

import java.time.LocalDateTime;

public record URLResponseDTO(
        Long id,
        String url,
        String shortCode,
        Integer timesAccessed,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}