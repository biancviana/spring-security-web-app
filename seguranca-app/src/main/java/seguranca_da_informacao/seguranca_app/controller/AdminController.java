package seguranca_da_informacao.seguranca_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import seguranca_da_informacao.seguranca_app.service.UserService;

@Controller
public class AdminController {

    @Autowired
    private UserService service;

    @GetMapping("/cadastro")
    public String cadastroPage() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(String username, String password, String role) {
        service.save(username, password, role);
        return "redirect:/administradores";
    }

    @GetMapping("/administradores")
    public String adminPage() {
        return "admin";
    }
}
