package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.LoginDTO;
import com.holis.san01.model.TokenResponse;
import com.holis.san01.model.Usuario;
import com.holis.san01.model.UsuarioDTO;
import com.holis.san01.repository.UsuarioRepository;
import com.holis.san01.security.JwtGenerator;
import com.holis.san01.util.Mapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtGenerator jwtGenerator;
//    private final JavaMailSender mailSender;

    public TokenResponse login(final LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getUsername())
                .orElseThrow(() -> new NotFoundRequestException("Usuário não encontrado"));

        return jwtGenerator.generateToken(authentication, usuario);
    }

    /**
     * Ler o usuario ativo por email
     */
    public UsuarioDTO lerUsuarioPorEmail(final String email) {
        Usuario usr = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundRequestException("Usuário não encontrado"));
        return Mapper.mapTo(usr, UsuarioDTO.class);
    }

    /**
     * O usuario esqueceu sua senha
     * Gerar um numero randomico e enviar por email
     */
    public void enviarNovaSenha(final String email) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Random r = new Random();
        long nova = (long) (r.nextDouble() * 1000000);
        Optional<Usuario> opt = usuarioRepository.findByEmail(email);

        if (opt.isEmpty())
            throw new ApiRequestException("Email não encontrado no sistema!");

        Usuario usr = opt.get();
        usr.setNovaSenha(encoder.encode(Long.toString(nova)));
        usr.setDtRecuperacao(new Date());

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
