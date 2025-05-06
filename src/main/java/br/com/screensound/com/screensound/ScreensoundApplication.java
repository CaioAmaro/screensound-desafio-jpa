package br.com.screensound.com.screensound;

import br.com.screensound.com.screensound.main.Principal;
import br.com.screensound.com.screensound.repository.ArtistaRepository;
import br.com.screensound.com.screensound.repository.MusicasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreensoundApplication implements CommandLineRunner {

	@Autowired
	private ArtistaRepository artistaRepository;
	@Autowired
	private MusicasRepository musicasRepository;

	public static void main(String[] args){
		SpringApplication.run(ScreensoundApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(artistaRepository, musicasRepository);
		principal.execute();
	}
}
