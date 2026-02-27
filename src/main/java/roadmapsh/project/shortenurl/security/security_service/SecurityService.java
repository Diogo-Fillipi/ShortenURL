package roadmapsh.project.shortenurl.security.security_service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import roadmapsh.project.shortenurl.DTOs.security.AuthenticationDTO;
import roadmapsh.project.shortenurl.DTOs.security.LoginResponseDTO;
import roadmapsh.project.shortenurl.DTOs.security.RegisterDTO;
import roadmapsh.project.shortenurl.models.UserModel;
import roadmapsh.project.shortenurl.repositories.UserRepository;
import roadmapsh.project.shortenurl.security.UserRoles;

import java.util.Optional;

@Service
public class SecurityService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public SecurityService(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public String encodePassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    public LoginResponseDTO login(AuthenticationDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }

    public boolean isThereAnyLogin(String login) {
        Optional<UserModel> userFound = this.userRepository.findByLogin(login);
        if (userFound.isPresent()) {
            return true;
        }
        return false;
    }

    public void registerUser(RegisterDTO user) {
        String encryptedPassword = encodePassword(user.password());
        UserModel newuser = new UserModel(user.login(), encryptedPassword);
        newuser.setRole(UserRoles.USER);
        this.userRepository.save(newuser);
    }

    public void changeRole(AuthenticationDTO user) {
        Optional<UserModel> actualUser = this.userRepository.findByLogin(user.login());
        UserModel updatedUser = actualUser.get();
        updatedUser.setRole(UserRoles.ADMIN);
        this.userRepository.save(updatedUser);
    }

}
