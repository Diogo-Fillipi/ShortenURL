/*
package roadmapsh.project.shortenurl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import roadmapsh.project.shortenurl.service.ShortenURLService;

public class URLServiceTest {

    ShortenURLService shortenUrlService;

    @BeforeEach
    public void setUp() {
        shortenUrlService = new ShortenURLService();
    }


    @Test
    void createShortURLTest(){

        //acao
        String originalURL = originalURLService.getOriginalURL();
        String shortenURL = shortenUrlService.createShortURL(originalURL);

        //verificacao
        Assertions.assertThat(ShortenURL).isNotNull();

    }

    @Test
    void getOriginalURL(){
        String shortenURL = shortenUrlService.getShortenURL();
        String originalURL = originalURLService.getOriginalURL(shortenURL);
        Assertions.assertThat(originalURL).isNotNull();


    }

    @Test
    void updateShortURL(){
        String ShortenURLBeforeUpdate = shortenUrlService.getShortenURL();
        String original = originalURLService.getOriginalURL();
        String updatedShortenURL = ShortenURLService.updateShortenURL();

        Assertions.assertThat(updatedShortenURL).isNotNull();
        Assertions.assertThat(original).isNotEqualTo(updatedShortenURL);

    }

    @Test
    void deleteShortURL(){

    }

    @Test
    void getURLStats(){

    }

}

 */
