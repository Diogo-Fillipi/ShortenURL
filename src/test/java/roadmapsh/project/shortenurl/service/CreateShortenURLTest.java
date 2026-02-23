package roadmapsh.project.shortenurl.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import roadmapsh.project.shortenurl.DTO.url.URLResponseDTO;
import roadmapsh.project.shortenurl.model.URLModel;
import roadmapsh.project.shortenurl.repository.URLRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateShortenURLTest {

    @Mock
    Base62EncoderService base62EncoderService;

    @Mock
    URLRepository urlRepository;

    @InjectMocks
    ShortenURLService shortenURLService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test to see if the method creates the short code")
    public void createShortenUrl() {
        //Arrange
        //Aqui eu estou simulando o comportamento do Encoder
        when(base62EncoderService.encode()).thenReturn("abc123");

        //Aqui eu estou simulando o comportamento do URLRepository, para procurar no banco de dados se esse shortcode já existe
        when(urlRepository.findByUrlHashCode("abc123")).thenReturn(Optional.empty());

        //Aqui eu estou simulando o comportamento de salvar no banco de dados
        when(urlRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));


        URLModel urlModel = new URLModel();
        urlModel.setOriginalURL("https://google.com");
        urlModel.setUrlHashCode("abc123");
        urlModel.setTimesAccessed(0);

        when(urlRepository.findByUrlHashCode(any()))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(urlModel));

        URLResponseDTO response = shortenURLService.createShortenURL("https://google.com");

        assertNotNull(response);
        assertEquals("abc123", response.shortCode());
        assertEquals("https://google.com", response.url());

    }

}
