package roadmapsh.project.shortenurl.services.url_services;

import io.seruco.encoding.base62.Base62;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class Base62EncoderService {

    private final Base62 base62 = Base62.createInstance();

    public String encode() {
        long max = (long) Math.pow(62, 6);
        long n = ThreadLocalRandom.current().nextLong(max);
        byte[] bytes = ByteBuffer.allocate(Long.BYTES).putLong(n).array();
        final byte[] encoded = base62.encode(bytes);
        String encodedString = new String(encoded, StandardCharsets.UTF_8);

        return encodedString;
    }


}
