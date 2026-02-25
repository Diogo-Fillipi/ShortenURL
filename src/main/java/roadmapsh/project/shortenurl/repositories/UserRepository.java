package roadmapsh.project.shortenurl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import roadmapsh.project.shortenurl.models.UserModel;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByLogin(String login);

}
