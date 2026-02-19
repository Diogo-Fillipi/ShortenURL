package roadmapsh.project.shortenurl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import roadmapsh.project.shortenurl.security.User;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String login);

}
