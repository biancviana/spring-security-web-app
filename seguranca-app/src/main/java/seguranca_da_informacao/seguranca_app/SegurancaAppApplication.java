package seguranca_da_informacao.seguranca_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SegurancaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SegurancaAppApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("1234"));
	}


}
