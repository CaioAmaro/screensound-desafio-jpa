package br.com.screensound.com.screensound.repository;

import br.com.screensound.com.screensound.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Optional<Artista> findByNome(String nomeArtista);
}

