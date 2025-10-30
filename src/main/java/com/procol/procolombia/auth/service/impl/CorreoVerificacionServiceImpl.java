package com.procol.procolombia.auth.service.impl;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.CorreoVerificacionResponseDTO;
import com.procol.procolombia.auth.entities.CorreoVerificacion;
import com.procol.procolombia.auth.exception.notfound.CorreoVerificacionNotFoundException;
import com.procol.procolombia.auth.mappers.CorreoVerificacionMapper;
import com.procol.procolombia.auth.repositories.CorreoVerificacionRepository;
import com.procol.procolombia.auth.service.CorreoVerificacionService;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class CorreoVerificacionServiceImpl implements CorreoVerificacionService {

    private final CorreoVerificacionRepository correoRepository;
    private final CorreoVerificacionMapper correoVerificacionMapper;
    private final String apiKey;
    private final String fromEmail;

    public CorreoVerificacionServiceImpl(
            CorreoVerificacionRepository correoRepository,
            CorreoVerificacionMapper correoVerificacionMapper,
            @Value("${sendgrid.api.key}") String apiKey,
            @Value("${sendgrid.from.email}") String fromEmail
    ) {
        this.correoRepository = correoRepository;
        this.correoVerificacionMapper = correoVerificacionMapper;
        this.apiKey = apiKey;
        this.fromEmail = fromEmail;
    }

    // -----------------------------------------------------
    // ENVIAR CÓDIGO DE VERIFICACIÓN
    // -----------------------------------------------------
    @Override
    public ApiResponseDTO<String> enviarVerificarCorreo(String correoVerificacion) {
        CorreoVerificacion correo = correoRepository.findByIdCorreo(correoVerificacion)
                .orElseThrow(() -> new CorreoVerificacionNotFoundException("Correo no encontrado: " + correoVerificacion));

        LocalDateTime ahora = LocalDateTime.now();

        // Si ya está verificado
        if (correo.getEstadoCorreoVerificado() == 3) {
            return new ApiResponseDTO<>(400, "Ya verificado", "El correo ya fue verificado.", ahora.toString());
        }

        // Generar nuevo PIN y cambiar estado a pendiente (2)
        String pin = generarPin();
        correo.setPinCorreo(pin);
        correo.setEstadoCorreoVerificado((short) 2);
        correoRepository.save(correo);

        // Enviar correo
        int status = enviarCorreo(correoVerificacion, pin);
        if (status >= 200 && status < 300) {
            return new ApiResponseDTO<>(200, "Código enviado", "Se ha enviado el código de verificación al correo.", ahora.toString());
        } else {
            return new ApiResponseDTO<>(500, "Error enviando correo", "No se pudo enviar el correo.", ahora.toString());
        }
    }

    // -----------------------------------------------------
    // VERIFICAR CÓDIGO
    // -----------------------------------------------------
    @Override
    public ApiResponseDTO<String> verificarCorreo(String correoVerificacion, String codigo) {
        CorreoVerificacion correo = correoRepository.findByIdCorreo(correoVerificacion)
                .orElseThrow(() -> new CorreoVerificacionNotFoundException("Correo no encontrado: " + correoVerificacion));

        LocalDateTime ahora = LocalDateTime.now();

        if (correo.getPinCorreo() == null) {
            return new ApiResponseDTO<>(400, "Sin código", "Debes solicitar un código de verificación primero.", ahora.toString());
        }

        if (correo.getPinCorreo().equals(codigo)) {
            correo.setEstadoCorreoVerificado((short) 3); // Verificado
            correo.setPinCorreo(null); // limpiar
            correoRepository.save(correo);
            return new ApiResponseDTO<>(200, "Éxito", "Correo verificado correctamente.", ahora.toString());
        } else {
            return new ApiResponseDTO<>(400, "Código incorrecto", "El código ingresado no es válido.", ahora.toString());
        }
    }

    // -----------------------------------------------------
    // OBTENER ESTADO DEL CORREO
    // -----------------------------------------------------
    @Override
    public ApiResponseDTO<CorreoVerificacionResponseDTO> obtenerPorCorreo(String correoVerificacion) {
        CorreoVerificacion correo = correoRepository.findByIdCorreo(correoVerificacion)
                .orElseThrow(() -> new CorreoVerificacionNotFoundException("Correo no encontrado: " + correoVerificacion));

        return new ApiResponseDTO<>(
                200,
                "Correo encontrado",
                correoVerificacionMapper.toDto(correo),
                LocalDateTime.now().toString()
        );
    }

    // -----------------------------------------------------
    // MÉTODOS AUXILIARES
    // -----------------------------------------------------
    private String generarPin() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    private int enviarCorreo(String toEmail, String codigo) {
        try {
            Email from = new Email(fromEmail);
            Email to = new Email(toEmail);
            String subject = "Verificación de correo electrónico";
            Content content = new Content("text/html",
                    "<h3>Verifica tu correo</h3>" +
                            "<p>Tu código de verificación es:</p>" +
                            "<h2>" + codigo + "</h2>" +
                            "<p>Este código expirará en 5 minutos.</p>");
            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid(apiKey);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            return response.getStatusCode();
        } catch (IOException ex) {
            return 500;
        }
    }
}
