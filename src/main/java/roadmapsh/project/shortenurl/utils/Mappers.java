package roadmapsh.project.shortenurl.utils;


import roadmapsh.project.shortenurl.DTO.URLResponseDTO;
import roadmapsh.project.shortenurl.model.URLModel;

public class Mappers {

    public static URLResponseDTO urlModelToUrlResponseDTO(URLModel urlModel) {
        URLResponseDTO urlResponseDTO = new URLResponseDTO(
                urlModel.getUrlId(),
                urlModel.getOriginalURL(),
                urlModel.getUrlHashCode(),
                urlModel.getTimesAccessed(),
                urlModel.getCreatedAt(),
                urlModel.getUpdatedAt());
        return urlResponseDTO;
    }
}
