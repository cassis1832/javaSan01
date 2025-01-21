package com.holis.san01.controller.login;

import com.holis.san01.model.LoginDTO;
import com.holis.san01.model.TokenResponse;
import com.holis.san01.repository.UsuarioRepository;
import com.holis.san01.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginDTO loginDTO) {

        TokenResponse tokenResponse = loginService.login(loginDTO);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    /**
     * Usuario esqueceu a senha
     */
    @GetMapping("/enviarSenha")
    public ResponseEntity<String> enviarSenha(
            @RequestParam(name = "email") String email) {

        loginService.enviarNovaSenha(email);
        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }
}
