package roadmapsh.project.shortenurl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import roadmapsh.project.shortenurl.DTOs.security.AuthenticationDTO;
import roadmapsh.project.shortenurl.DTOs.security.LoginResponseDTO;
import roadmapsh.project.shortenurl.DTOs.security.RegisterDTO;
import roadmapsh.project.shortenurl.models.UserModel;
import roadmapsh.project.shortenurl.repositories.UserRepository;
import roadmapsh.project.shortenurl.security.UserRoles;
import roadmapsh.project.shortenurl.security.security_service.SecurityService;
import roadmapsh.project.shortenurl.security.security_service.TokenService;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    private final UserRepository userRepository;

    private final SecurityService securityService;


    public AuthenticationController(UserRepository userRepository, SecurityService securityService) {
        this.userRepository = userRepository;
        this.securityService = securityService;

    }


    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Validated AuthenticationDTO authenticationDTO) {
        return ResponseEntity.ok(securityService.login(authenticationDTO));
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody @Validated RegisterDTO registerDTO) {
        if(securityService.isThereAnyLogin(registerDTO.login()) == true) return ResponseEntity.badRequest().build();
        securityService.registerUser(registerDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/changerole")
    public ResponseEntity changerole (@RequestBody @Validated AuthenticationDTO authenticationDTO) {
        if(securityService.isThereAnyLogin(authenticationDTO.login()) == true) return ResponseEntity.badRequest().build();
        securityService.changeRole(authenticationDTO);
        return ResponseEntity.ok().build();
    }
}
