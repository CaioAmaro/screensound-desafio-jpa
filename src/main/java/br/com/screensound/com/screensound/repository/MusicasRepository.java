package br.com.screensound.com.screensound.repository;

import br.com.screensound.com.screensound.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicasRepository extends JpaRepository<Musica, Long> {

}
