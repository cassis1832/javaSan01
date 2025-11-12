package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.mapper.UsuarioMapper;
import com.holis.san01.model.LoginDto;
import com.holis.san01.model.Usuario;
import com.holis.san01.model.UsuarioDto;
import com.holis.san01.model.local.TokenResponse;
import com.holis.san01.repository.UsuarioRepository;
import com.holis.san01.security.JwtGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtGenerator jwtGenerator;
    private final UsuarioMapper usuarioMapper;

//    private final JavaMailSender mailSender;

    @Transactional
    public TokenResponse login(final LoginDto loginDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getUsername())
                .orElseThrow(() -> new ApiRequestException("Usuário não encontrado"));

        return jwtGenerator.generateToken(authentication, usuario);
    }

    /**
     * Ler o usuario ativo por email
     */
    public UsuarioDto lerUsuarioPorEmail(final String email) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("Usuário não encontrado"));
        return usuarioMapper.toDto(usuario);
    }

    /**
     * O usuario esqueceu sua senha
     * Gerar um numero randomico e enviar por email
     */
    @Transactional
    public void enviarNovaSenha(final String email) {

        Optional<Usuario> opt = usuarioRepository.findByEmail(email);

        if (opt.isEmpty())
            throw new ApiRequestException("Email não encontrado no sistema!");

        Usuario usr = opt.get();

        int numero = ThreadLocalRandom.current().nextInt(0, 1000000); // 0 a 999999
        String numeroStr = String.format("%06d", numero); // Preenche com zeros à esquerda
        System.out.println("Número formatado: " + numeroStr); // Ex: "001234", "999999"

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usr.setNovaSenha(encoder.encode(numeroStr));
        usr.setDtRecuperacao(LocalDate.now());

        var conteudo = "Use a senha abaixo em seu próximo Login.<br/>";
        conteudo = conteudo + numeroStr;
        conteudo = conteudo
                + "<br/><br/>Desconsidere este email caso você não tenha solicitado a alteração da senha.";

//        try {
//            MimeMessage mail = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mail);
//            helper.setFrom("suporte@powerfin.com.br");
//            helper.setTo(email);
//            helper.setSubject("Alteração de Senha");
//            helper.setText(conteudo, true);
//            mailSender.send(mail);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
