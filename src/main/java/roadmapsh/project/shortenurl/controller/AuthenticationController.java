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

    @Autowired
    AuthenticationManager authenticationManager;

    UserRepository userRepository;

    SecurityService securityService;

    TokenService tokenService;

    public AuthenticationController(UserRepository userRepository, SecurityService securityService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.tokenService = tokenService;
    }


    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Validated AuthenticationDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody @Validated RegisterDTO registerDTO) {
        Optional<UserModel> actualUser = this.userRepository.findByLogin(registerDTO.login());
        if(actualUser.isPresent()) return  ResponseEntity.badRequest().build();
        String encryptedPassword = securityService.encodePassword(registerDTO.password());
        UserModel newuser = new UserModel(registerDTO.login(), encryptedPassword);
        newuser.setRole(UserRoles.USER);
        this.userRepository.save(newuser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/changerole")
    public ResponseEntity changerole (@RequestBody @Validated AuthenticationDTO authenticationDTO) {
        Optional<UserModel>actualUser = this.userRepository.findByLogin(authenticationDTO.login());
        if(actualUser.isEmpty()) return  ResponseEntity.badRequest().build();
        UserModel user = actualUser.get();
        user.setRole(UserRoles.ADMIN);
        this.userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
