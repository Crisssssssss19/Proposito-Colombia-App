package com.procol.procolombia.auth.service.impl;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.entities.PreRegistro;
import com.procol.procolombia.auth.exception.notfound.PreRegistroNotFountException;
import com.procol.procolombia.auth.repositories.PreRegistroRepository;
import com.procol.procolombia.auth.service.PreRegistroService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PreRegistroServiceImpl implements PreRegistroService {

    private final PreRegistroRepository preRegistroRepository;

    private final String accountSid;
    private final String authToken;
    private final String twilioPhoneNumber;

    private final int tiempoExpiracionCodigo = 5; // minutos

    public PreRegistroServiceImpl(PreRegistroRepository preRegistroRepository, @Value("${twilio.accountSid}") String accountSid, @Value("${twilio.authToken}") String authToken, @Value("${twilio.phoneNumber}") String twilioPhoneNumber) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.twilioPhoneNumber = twilioPhoneNumber;
        this.preRegistroRepository = preRegistroRepository;
    }

    @Override
    public ApiResponseDTO<String> enviarCodigo(String telefono) {
        PreRegistro pre = preRegistroRepository.findById(telefono).orElse(null);
        LocalDateTime ahora = LocalDateTime.now();

        if (pre == null || pre.getIntentos()==0) {
            // Primer intento
            pre = new PreRegistro();
            pre.setIdPreRegistro(telefono);
            pre.setPinPreRegistro(generarPin());
            pre.setFechaPreRegistro(ahora);
            pre.setEstadoPreRegistro((short) 1);
            pre.setIntentos(1);
            pre.setBloqueadoHasta(null);

            preRegistroRepository.save(pre);
            enviarSms(telefono, pre.getPinPreRegistro());
            return new ApiResponseDTO<>(200, "Normal", "Código enviado", ahora.toString());
        }

        // Bloqueado
        if (pre.getEstadoPreRegistro() == 3 && pre.getBloqueadoHasta() != null) {
            if(ahora.isAfter((pre.getBloqueadoHasta()))){
                // Desbloquear
                pre.setEstadoPreRegistro((short) 1);
                pre.setIntentos(0);
                pre.setBloqueadoHasta(null);
                preRegistroRepository.save(pre);
            } else {
                return new ApiResponseDTO<>(403, "Bloqueado", "Número bloqueado hasta "+pre.getBloqueadoHasta(), ahora.toString());
            }
        }

        // Ya existe -> incrementar intentos
        pre.setIntentos(pre.getIntentos() + 1);

        if (pre.getIntentos() == 2) {
            pre.setEstadoPreRegistro((short) 2);
            pre.setPinPreRegistro(generarPin());
            pre.setFechaPreRegistro(ahora);
            preRegistroRepository.save(pre);

            enviarSms(telefono, pre.getPinPreRegistro());
            return new ApiResponseDTO<>(200, "Intento_2", "Código enviado", ahora.toString());

        } else if (pre.getIntentos() == 3) {
            pre.setEstadoPreRegistro((short) 2);
            pre.setPinPreRegistro(generarPin());
            pre.setFechaPreRegistro(ahora);
            preRegistroRepository.save(pre);

            enviarSms(telefono, pre.getPinPreRegistro());
            return new ApiResponseDTO<>(200, "Intento_3", "Código enviado", ahora.toString());

        } else if (pre.getIntentos() >= 4) {
            pre.setEstadoPreRegistro((short) 3);
            pre.setBloqueadoHasta(ahora.plusHours(1)); // bloqueado 1 hora
            preRegistroRepository.save(pre);

            return new ApiResponseDTO<>(403, "Bloqueado", "Número bloqueado por exceso de intentos", ahora.toString());
        }

        return new ApiResponseDTO<>(500, "Error", "Caso no controlado", ahora.toString());
    }

    private String generarPin() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    private void enviarSms(String telefono, String pin) {
        Twilio.init(accountSid, authToken);
        Message.creator(
                new PhoneNumber(telefono),
                new PhoneNumber(twilioPhoneNumber),
                "Su código de verificación es: " + pin
        ).create();
    }

    @Override
    public ApiResponseDTO<String> validarCodigo(String telefono, String codigo) {
        PreRegistro pre = preRegistroRepository.findById(telefono)
                .orElseThrow(() -> new PreRegistroNotFountException("No se encontró un pre-registro para el número de teléfono proporcionado"));
        LocalDateTime ahora = LocalDateTime.now();

        if(pre.getEstadoPreRegistro() == 3
            && pre.getBloqueadoHasta() != null
            && pre.getBloqueadoHasta().isAfter(ahora)) {
            return new ApiResponseDTO<>(403, "Número bloqueado hasta " + pre.getBloqueadoHasta(), null, ahora.toString());
        }
        if (pre.getPinPreRegistro().equals(codigo)) {
            pre.setEstadoPreRegistro((short) 1);
            preRegistroRepository.save(pre);
            return new ApiResponseDTO<>(200, "Verificación exitosa", "Número verificado", ahora.toString());
        } else {
            if(pre.getEstadoPreRegistro() == 1){
                pre.setEstadoPreRegistro((short)2);
                preRegistroRepository.save(pre);
                return new ApiResponseDTO<>(401, "Código incorrecto. Último intento antes de bloqueo.", null, ahora.toString());
            } else if (pre.getEstadoPreRegistro() == 2) {
                pre.setEstadoPreRegistro((short)3);
                pre.setBloqueadoHasta(ahora.plusMinutes(tiempoExpiracionCodigo));
                preRegistroRepository.save(pre);
                return new ApiResponseDTO<>(403, "Número bloqueado hasta " + pre.getBloqueadoHasta(), null, ahora.toString());
            }
            return new ApiResponseDTO<>(401, "Código incorrecto.", null, ahora.toString());
        }
    }
}
