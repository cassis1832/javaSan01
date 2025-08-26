package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.LoginDTO;
import com.holis.san01.model.TokenResponse;
import com.holis.san01.model.Usuario;
import com.holis.san01.model.UsuarioDTO;
import com.holis.san01.repository.UsuarioRepository;
import com.holis.san01.security.JwtGenerator;
import com.holis.san01.util.Mapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtGenerator jwtGenerator;
//    private final JavaMailSender mailSender;

    @Transactional
    public TokenResponse login(final LoginDTO loginDTO) {
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
    public UsuarioDTO lerUsuarioPorEmail(final String email) {
        Usuario usr = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ApiRequestException("Usuário não encontrado"));
        return Mapper.mapTo(usr, UsuarioDTO.class);
    }

    /**
     * O usuario esqueceu sua senha
     * Gerar um numero randomico e enviar por email
     */
    @Transactional
    public void enviarNovaSenha(final String email) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Random r = new Random();
        long nova = (long) (r.nextDouble() * 1000000);
        Optional<Usuario> opt = usuarioRepository.findByEmail(email);

        if (opt.isEmpty())
            throw new ApiRequestException("Email não encontrado no sistema!");

        Usuario usr = opt.get();
        usr.setNovaSenha(encoder.encode(Long.toString(nova)));
        usr.setDtRecuperacao(LocalDate.now());

        var conteudo = "Use a senha abaixo em seu próximo Login.<br/>";
        conteudo = conteudo + nova;
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
