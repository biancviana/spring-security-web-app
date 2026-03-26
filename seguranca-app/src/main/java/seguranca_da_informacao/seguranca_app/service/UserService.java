package seguranca_da_informacao.seguranca_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import seguranca_da_informacao.seguranca_app.model.User;
import seguranca_da_informacao.seguranca_app.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public void save(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole("ROLE_" + role);
        repository.save(user);
    }
}
