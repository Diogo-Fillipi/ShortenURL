package roadmapsh.project.shortenurl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import roadmapsh.project.shortenurl.DTO.URLResponseDTO;
import roadmapsh.project.shortenurl.model.URLModel;
import roadmapsh.project.shortenurl.repository.URLRepository;
import roadmapsh.project.shortenurl.service.Base62EncoderService;
import roadmapsh.project.shortenurl.service.ShortenURLService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShortenURLApplicationTest {

    @Test
    void contextLoads() {
    }

}
