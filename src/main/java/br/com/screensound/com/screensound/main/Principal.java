package br.com.screensound.com.screensound.main;

import br.com.screensound.com.screensound.model.Artista;
import br.com.screensound.com.screensound.model.Musica;
import br.com.screensound.com.screensound.model.TipoArtista;
import br.com.screensound.com.screensound.repository.ArtistaRepository;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner input = new Scanner(System.in);
    private ArtistaRepository artistaRepository;

    public Principal(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public void execute(){
        int opcao = -1;
        while(opcao != 0) {
            System.out.println("Screen Sound");
            System.out.println("""
                    1 - Cadastrar Artista(s).
                    2 - Cadastrar Musicas.
                    
                    0 - Sair
                    """);
            System.out.print("Escolha: ");
            opcao = input.nextInt();
            input.nextLine(); //LIMPA BUFFE

            switch (opcao){
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                default:
                    break;
            }
        }
    }

    private void cadastrarArtista(){
        try{
            System.out.println();
            System.out.println("Cadastro de Artista");
            System.out.print("Digite nome do artista: ");
            var nomeArtista = input.nextLine();
            System.out.println(nomeArtista.toUpperCase() + " é (SOLO, DUPLA, BANDA)?");
            System.out.print("Escolha o tipo de catégoria que mais encaixa: ");
            var tipoArtistaPortugues = input.nextLine();

            var tipoArtista = TipoArtista.fromTipoArtistaPortugues(tipoArtistaPortugues);
            Artista artista = new Artista(nomeArtista, tipoArtista);
            artistaRepository.save(artista);
            System.out.println(artista);
            System.out.println();
        }catch (DataIntegrityViolationException e){
            System.out.println("Já existe um artista com esse nome");
        }

    }

    private void buscarArtistas(){
        var artistas = artistaRepository.findAll();
        System.out.println("Artistas Cadastrados");
        artistas.forEach(a -> {
            System.out.println("Artista: " + a.getNome() + " Tipo: " + a.getTipoArtista() );
        });
    }

    private void cadastrarMusicas(){
        buscarArtistas();
        System.out.println("Essa música é de qual artistas?");
        System.out.print("R: ");
        var nomeArtista = input.nextLine().toUpperCase();

        Optional<Artista> artistaEncontrado = artistaRepository.findByNome(nomeArtista);

        if(artistaEncontrado.isPresent()){
            var artista = artistaEncontrado.get();

            System.out.println("Nome da Música: ");
            var nomeMusica = input.nextLine();
            System.out.println("Álbum: ");
            var album = input.nextLine();
            Musica musica = new Musica(nomeMusica, album);
            artista.setMusicas(musica);
            artistaRepository.save(artista);
            artista.getMusicas().forEach(System.out::println);

        }else{
            System.out.println("Artista não cadastrado no banco de dados.");
        }

    }
}
