//Inicialiação
//.\mvnw spring-boot:run

package com.api.rayssa;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RayssaApplication {
    private final Map<Integer, Usuario> usuarios = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(RayssaApplication.class, args);
    }

    @PostMapping("/usuario")
    public String cadastrarUsuario(@RequestBody Usuario usuario) {

        if (usuarios.containsKey(usuario.getId())) {
            return "Usuário com ID " + usuario.getId() + " já existe.";
        }

        usuarios.put(usuario.getId(), usuario);

        return "OK, usuário " + usuario.getLogin() + " criado.";
    }

    @GetMapping("/usuario/{id}")
    public String consultarUsuario(@PathVariable Integer id) {

        Usuario usuario = usuarios.get(id);

        if (usuario == null) {
            return "Usuário não encontrado.";
        }

        return "Listando usuário: " + usuario.getLogin();
    }

    // Classe interna para representar o usuário
    static class Usuario {
        private Integer id;
        private String login;
        private String senha;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }
    }
}