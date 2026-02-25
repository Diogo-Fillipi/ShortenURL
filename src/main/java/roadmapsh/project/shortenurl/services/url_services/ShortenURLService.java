package roadmapsh.project.shortenurl.services.url_services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import roadmapsh.project.shortenurl.DTOs.url.URLResponseDTO;
import roadmapsh.project.shortenurl.models.URLModel;
import roadmapsh.project.shortenurl.repositories.URLRepository;
import roadmapsh.project.shortenurl.utils.Mappers;

import java.util.Optional;

@Slf4j
@Service
public class ShortenURLService {

    @Autowired
    URLRepository urlRepository;

    @Autowired
    Base62EncoderService base62EncoderService;

    public ShortenURLService(URLRepository urlRepository, Base62EncoderService base62EncoderService) {
        this.urlRepository = urlRepository;
        this.base62EncoderService = base62EncoderService;
    }

    @Transactional
    public URLResponseDTO createShortenURL(String originalURL) {
        if(originalURL == null) return null;
        final int MAX_RETRIES = 5;
        for (int i = 0; i < MAX_RETRIES; i++) {
            String hashCode = base62EncoderService.encode();
            Optional<URLModel> urlResult = urlRepository.findByUrlHashCode(hashCode);
            if (!urlResult.isPresent()) {
                URLModel urlModel = new URLModel();
                urlModel.setOriginalURL(originalURL);
                urlModel.setTimesAccessed(0);
                urlModel.setUrlHashCode(hashCode);
                try {
                    this.urlRepository.save(urlModel);
                    Optional<URLModel> completeUrlModelResponse = this.urlRepository.findByUrlHashCode(hashCode);
                    if (completeUrlModelResponse.isPresent()) {
                        return Mappers.urlModelToUrlResponseDTO(completeUrlModelResponse.get());
                    }
                    return null;
                } catch (DataIntegrityViolationException e) {
                    //log.debug("Duplicated code: {}", hashCode);
                }
            }
        }

        throw new IllegalStateException("Max retries reached");
    }

    public String retrieveOriginalURL(String hashCode) {
        Optional<URLModel> shortenURL = urlRepository.findByUrlHashCode(hashCode);
        if (shortenURL.isPresent()) {
            return shortenURL.get().getOriginalURL();
        }
        return null;
    }

    @Transactional
    public void deleteShortenURL(String hashCode) {
        Optional<URLModel> shortenURL = urlRepository.findByUrlHashCode(hashCode);
        System.out.println(hashCode);
        if (shortenURL.isPresent()) {
            urlRepository.delete(shortenURL.get());
        }
    }

    @Transactional
    public URLResponseDTO updateShortenURL(String originalURLHashCode, String newURL) {
        Optional<URLModel> shortenURLModel = urlRepository.findByUrlHashCode(originalURLHashCode);
        System.out.println(shortenURLModel.isPresent());
        if (shortenURLModel.isPresent()) {
            URLModel previousURLModel = shortenURLModel.get();
            previousURLModel.setOriginalURL(newURL);
            urlRepository.save(previousURLModel);
            Optional<URLModel> updatedURLModel = urlRepository.findByUrlHashCode(originalURLHashCode);
            if (updatedURLModel.isPresent()) {
                return Mappers.urlModelToUrlResponseDTO(updatedURLModel.get());
            }
            return null;
        }
        return null;
    }

    public URLResponseDTO getStats(String hashcode) {
        Optional<URLModel> shortenURLModel = urlRepository.findByUrlHashCode(hashcode);
        if (shortenURLModel.isPresent()) {
            return Mappers.urlModelToUrlResponseDTO(shortenURLModel.get());
        }
        return null;
    }

}
