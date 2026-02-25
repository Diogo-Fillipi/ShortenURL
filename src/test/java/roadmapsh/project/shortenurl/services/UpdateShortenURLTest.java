package roadmapsh.project.shortenurl.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.ArgumentMatchers.*;
import org.mockito.MockitoAnnotations;
import roadmapsh.project.shortenurl.DTOs.url.URLResponseDTO;
import roadmapsh.project.shortenurl.models.URLModel;
import roadmapsh.project.shortenurl.repositories.URLRepository;
import roadmapsh.project.shortenurl.services.url_services.ShortenURLService;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class UpdateShortenURLTest {

    @Mock
    private URLRepository urlRepository;

    @InjectMocks
    private ShortenURLService shortenURLService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test to see if the shortenURL is updated properly in the DB")
    public void UpdateShortenURL() {
        //arrange
        String hashcode = "hashcode";
        URLModel previous = new URLModel();
        previous.setUrlHashCode(hashcode);
        previous.setOriginalURL("http://old.com");

        String newURL = "http://new.com";
        URLModel actual = new URLModel();
        actual.setUrlHashCode(hashcode);
        actual.setOriginalURL(newURL);

        when(this.urlRepository.findByUrlHashCode(hashcode))
                .thenReturn(Optional.of(previous))
                .thenReturn(Optional.of(actual));

        when(this.urlRepository.save(ArgumentMatchers.any(URLModel.class))).thenReturn(actual);

        URLResponseDTO response = this.shortenURLService.updateShortenURL(hashcode, newURL);

        assertNotNull(response);

        assertEquals(hashcode, response.shortCode());
        assertEquals("http://new.com", response.url());

        verify(this.urlRepository).save(argThat(model ->
                model.getOriginalURL().equals(newURL)));

        verify(this.urlRepository).save(argThat(model ->
                model.getUrlHashCode().equals(hashcode)));

        verify(this.urlRepository, times(2)).findByUrlHashCode(hashcode);


    }

}
