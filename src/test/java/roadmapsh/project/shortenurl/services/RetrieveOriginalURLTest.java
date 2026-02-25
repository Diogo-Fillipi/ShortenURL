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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class RetrieveOriginalURLTest {

    @Mock
    URLRepository urlRepository;

    @InjectMocks
    ShortenURLService shortenURLService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test to see if we can retrieve the URL from the hashcode ")
    public void retrieveOriginalURL() {

        //Criamos o URLModel de exemplo
        String hashcode = "abc123";
        URLModel urlModel = new URLModel();
        urlModel.setOriginalURL("http://example.com");
        urlModel.setUrlHashCode(hashcode);

        //Simulamos o comportamento do banco de dados
        when(urlRepository.findByUrlHashCode(hashcode))
                .thenReturn(Optional.of(urlModel));

        String originalURL = shortenURLService.retrieveOriginalURL("abc123");

        assertNotNull(originalURL);
        assertEquals("http://example.com", originalURL);


    }

}