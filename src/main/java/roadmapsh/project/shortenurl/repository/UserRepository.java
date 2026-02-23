package roadmapsh.project.shortenurl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import roadmapsh.project.shortenurl.model.UserModel;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByLogin(String login);

}
