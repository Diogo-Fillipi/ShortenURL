package roadmapsh.project.shortenurl.DTOs.url;

import java.time.LocalDateTime;

public record URLResponseDTO(
        Long id,
        String url,
        String shortCode,
        Integer timesAccessed,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}