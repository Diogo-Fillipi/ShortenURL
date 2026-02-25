package roadmapsh.project.shortenurl.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import roadmapsh.project.shortenurl.models.URLModel;
import roadmapsh.project.shortenurl.repositories.URLRepository;
import roadmapsh.project.shortenurl.services.url_services.ShortenURLService;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeleteShortenURLTest {

    @Mock
    private URLRepository urlRepository;

    @InjectMocks
    ShortenURLService shortenURLService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test to see if the shorten url is deleted properly when we have the parameter")
    public void DeleteShortenURL() {
        URLModel urlModel = new URLModel();
        String hashcode = "abc123";
        urlModel.setOriginalURL("http://example.com");
        urlModel.setUrlHashCode(hashcode);
        when(this.urlRepository.findByUrlHashCode(hashcode)).thenReturn(Optional.of(urlModel));

        this.shortenURLService.deleteShortenURL(hashcode);

        verify(this.urlRepository).delete(urlModel);

    }

}
