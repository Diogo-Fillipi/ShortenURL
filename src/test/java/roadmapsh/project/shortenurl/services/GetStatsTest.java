package roadmapsh.project.shortenurl.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import roadmapsh.project.shortenurl.DTOs.url.URLResponseDTO;
import roadmapsh.project.shortenurl.models.URLModel;
import roadmapsh.project.shortenurl.repositories.URLRepository;
import roadmapsh.project.shortenurl.services.url_services.ShortenURLService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetStatsTest {

    @Mock
    private URLRepository urlRepository;

    @InjectMocks
    private ShortenURLService shortenURLService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    @DisplayName("Test to see if the stats are retrieved properly")
    public void getStats() {
        URLModel urlModel = new URLModel();
        String hashCode = "abc123";

        urlModel.setOriginalURL("http://example.com");
        urlModel.setUrlHashCode(hashCode);
        when(this.urlRepository.findByUrlHashCode(hashCode)).thenReturn(Optional.of(urlModel));
        URLResponseDTO response = this.shortenURLService.getStats(hashCode);

        verify(urlRepository).findByUrlHashCode(hashCode);

        assertEquals(hashCode, response.shortCode());
        assertEquals("http://example.com", response.url());



    }


}
