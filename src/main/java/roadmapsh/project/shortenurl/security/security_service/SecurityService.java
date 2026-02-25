package roadmapsh.project.shortenurl.security.security_service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private final PasswordEncoder passwordEncoder;

    public SecurityService(PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;

    }

    public String encodePassword(String password) {
        return this.passwordEncoder.encode(password);
    }

}
