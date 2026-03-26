package seguranca_da_informacao.seguranca_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seguranca_da_informacao.seguranca_app.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<seguranca_da_informacao.seguranca_app.model.User> findByUsername(String username);
}
