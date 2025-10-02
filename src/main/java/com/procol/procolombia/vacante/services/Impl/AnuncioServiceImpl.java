package com.procol.procolombia.vacante.services.Impl;

import com.procol.procolombia.vacante.services.AnuncioService;
import com.procol.procolombia.vacante.dto.AnuncioDto;
import com.procol.procolombia.vacante.entities.Anuncio;
import com.procol.procolombia.vacante.entities.Vacante;
import com.procol.procolombia.vacante.mappers.AnuncioMapper;
import com.procol.procolombia.vacante.repositories.AnuncioRepository;
import com.procol.procolombia.vacante.repositories.VacanteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.net.MalformedURLException;



@Service
public class AnuncioServiceImpl implements AnuncioService {
    private static final String UPLOAD_DIR = "uploads";

    private final AnuncioRepository anuncioRepository;
    private final VacanteRepository vacanteRepository;
    private final AnuncioMapper anuncioMapper;

    public AnuncioServiceImpl(AnuncioRepository anuncioRepository,
                              VacanteRepository vacanteRepository,
                              AnuncioMapper anuncioMapper) {
        this.anuncioRepository = anuncioRepository;
        this.vacanteRepository = vacanteRepository;
        this.anuncioMapper = anuncioMapper;
    }

    @Override
    @Transactional
    public AnuncioDto createAnuncio(Integer idVacante, MultipartFile file) {
        try {

            Vacante vacante = vacanteRepository.findById(idVacante)
                    .orElseThrow(() -> new EntityNotFoundException("Vacante con id " + idVacante + " no encontrada"));

            Files.createDirectories(Paths.get(UPLOAD_DIR));

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            Files.write(filePath, file.getBytes());

            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            String tamanio = bufferedImage.getWidth() + "x" + bufferedImage.getHeight();
            String tipo = file.getContentType();


            Anuncio entity = new Anuncio();
            entity.setIdVacante(vacante);
            entity.setNombrePublicoAnuncio(file.getOriginalFilename());
            entity.setNombrePrivadoAnuncio(filePath.toString());
            entity.setTipoAnuncio(tipo);
            entity.setTamanioAnuncio(tamanio);


            Anuncio saved = anuncioRepository.save(entity);
            return anuncioMapper.toDto(saved);

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen del anuncio", e);
        }
    }


@Override
@Transactional
    public AnuncioDto updateAnuncio(Integer id, MultipartFile file) {
    try {
        Anuncio existing = anuncioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Anuncio con id " + id + " no existe"));

        Files.createDirectories(Paths.get(UPLOAD_DIR));

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        Files.write(filePath, file.getBytes());

        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        String tamanio = bufferedImage.getWidth() + "x" + bufferedImage.getHeight();
        String tipo = file.getContentType();

        existing.setNombrePublicoAnuncio(file.getOriginalFilename());
        existing.setNombrePrivadoAnuncio(filePath.toString());
        existing.setTipoAnuncio(tipo);
        existing.setTamanioAnuncio(tamanio);

        Anuncio updated = anuncioRepository.save(existing);
        return anuncioMapper.toDto(updated);

    } catch (IOException e) {
        throw new RuntimeException("Error al actualizar la imagen del anuncio", e);
    }
    }

    @Override
    public void deleteAnuncio(Integer id) {
        if (!anuncioRepository.existsById(id)) {
            throw new EntityNotFoundException("Anuncio con id " + id + " no existe");
        }
        anuncioRepository.deleteById(id);
    }

    @Override
    public AnuncioDto findById(Integer id) {
        return anuncioRepository.findById(id)
                .map(anuncioMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Anuncio con id " + id + " no existe"));
    }

    @Override
    public List<AnuncioDto> findAll() {
        return anuncioRepository.findAll()
                .stream()
                .map(anuncioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnuncioDto findByVacanteId(Integer vacanteId) {
        return anuncioRepository.findByIdVacante_Id(vacanteId)
                .map(anuncioMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Anuncio con vacanteId " + vacanteId + " no existe"));
    }

    @Override
    public Resource verImagen(Integer idAnuncio) {
        Anuncio anuncio = anuncioRepository.findById(idAnuncio)
                .orElseThrow(() -> new EntityNotFoundException("Anuncio con id " + idAnuncio + " no existe"));

        Path path = Paths.get(anuncio.getNombrePrivadoAnuncio());

        try {
            Resource recurso = new UrlResource(path.toUri());
            if (!recurso.exists() || !recurso.isReadable()) {
                throw new RuntimeException("No se pudo leer la imagen del anuncio con id " + idAnuncio);
            }
            return recurso;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error cargando la imagen", e);
        }
    }

}
