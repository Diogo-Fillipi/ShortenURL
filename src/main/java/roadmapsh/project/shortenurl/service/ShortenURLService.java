package roadmapsh.project.shortenurl.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import roadmapsh.project.shortenurl.model.URLModel;
import roadmapsh.project.shortenurl.repository.URLRepository;

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
    public String createShortenURL(String originalURL) {

        final int MAX_RETRIES = 5;
        for(int i = 0; i < MAX_RETRIES; i++) {
            String hashCode = base62EncoderService.encode();
            Optional<URLModel> urlResult = urlRepository.findByUrlHashCode(hashCode);
            if(!urlResult.isPresent()) {
                URLModel urlModel = new URLModel();
                urlModel.setOriginalURL(originalURL);
                urlModel.setTimesAccessed(0);
                urlModel.setUrlHashCode(hashCode);
                try{
                    this.urlRepository.save(urlModel);
                    System.out.println(originalURL);
                    return hashCode;
                }catch(DataIntegrityViolationException e){
                    //log.debug("Duplicated code: {}", hashCode);
                }
            }
        }

        throw new IllegalStateException("Max retries reached");
    }

    public String retrieveOriginalURL(String hashCode) {
        Optional<URLModel> shortenURL = urlRepository.findByUrlHashCode(hashCode);
        if(shortenURL.isPresent()) {
            URLModel shortenURLModel = shortenURL.get();
            shortenURLModel.setTimesAccessed(shortenURLModel.getTimesAccessed() + 1);
            urlRepository.save(shortenURLModel);
            return shortenURL.get().getOriginalURL();
        }
        return null;
    }

    @Transactional
    public void deleteShortenURL(String hashCode) {
        Optional<URLModel> shortenURL = urlRepository.findByUrlHashCode(hashCode);
        System.out.println(hashCode);
        if(shortenURL.isPresent()) {
            urlRepository.delete(shortenURL.get());
        }
    }


}
