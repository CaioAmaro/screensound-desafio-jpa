package br.com.screensound.com.screensound.main;

import br.com.screensound.com.screensound.model.Artista;
import br.com.screensound.com.screensound.model.Musica;
import br.com.screensound.com.screensound.model.TipoArtista;
import br.com.screensound.com.screensound.repository.ArtistaRepository;
import br.com.screensound.com.screensound.repository.MusicasRepository;
import br.com.screensound.com.screensound.service.ConsumoApiGemini;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner input = new Scanner(System.in);
    private ArtistaRepository artistaRepository;
    private MusicasRepository musicasRepository;

    public Principal(ArtistaRepository artistaRepository, MusicasRepository musicasRepository) {
        this.artistaRepository = artistaRepository;
        this.musicasRepository = musicasRepository;
    }

    public void execute() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("Screen Sound");
            System.out.println("""
                    1 - Cadastrar Artista(s).
                    2 - Cadastrar Musicas.
                    3 - Listar Músicas Cadastradas.
                    4 - Buscar Música Por Artista.
                    5 - Sobre o Artista
                    
                    0 - Sair
                    """);
            System.out.print("Escolha: ");
            opcao = input.nextInt();
            input.nextLine(); //LIMPA BUFFE
            System.out.println();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicasCadastradas();
                    break;
                case 4:
                    listarMusicaPorArtista();
                    break;
                case 5:
                    SobreArtista();
                    break;
                default:
                    break;
            }
        }
    }

    private void cadastrarArtista() {
        try {
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
        } catch (DataIntegrityViolationException e) {
            System.out.println("Já existe um artista com esse nome");
        }

    }

    private void buscarArtistas() {
        var artistas = artistaRepository.findAll();
        System.out.println("Artistas Cadastrados");
        artistas.forEach(a -> {
            System.out.println("Artista: " + a.getNome() + " Tipo: " + a.getTipoArtista());
        });

        System.out.println();
    }

    private void cadastrarMusicas() {
        buscarArtistas();
        System.out.println("Essa música é de qual artistas?");
        System.out.print("R: ");
        var nomeArtista = input.nextLine().toUpperCase();

        Optional<Artista> artistaEncontrado = artistaRepository.findByNome(nomeArtista);

        if (artistaEncontrado.isPresent()) {
            var artista = artistaEncontrado.get();

            System.out.println("Nome da Música: ");
            var nomeMusica = input.nextLine();
            System.out.println("Álbum: ");
            var album = input.nextLine();
            Musica musica = new Musica(nomeMusica, album);
            artista.setMusicas(musica);
            artistaRepository.save(artista);
            System.out.println("Música cadastrada com sucesso!");
        } else {
            System.out.println("Artista não cadastrado no banco de dados.");
        }

    }

    private void listarMusicasCadastradas() {
        List<Musica> musicasCadastradas = musicasRepository.findAll();
        musicasCadastradas.forEach(m -> {
            System.out.println("Música:'" + m.getNomeMusica() + "' Albúm:'" + m.getAlbum() + "' Artista: '" + m.getArtista().getNome() + ":" + m.getArtista().getTipoArtista() + "'");
        });
    }

    private void listarMusicaPorArtista() {
        buscarArtistas();
        System.out.println("Digite o nome do artista para buscar as músicas.");
        System.out.print("R: ");
        var nomeArtistaProcurado = input.nextLine().toUpperCase();
        Optional<Artista> artistaEncontrado = artistaRepository.findByNome(nomeArtistaProcurado);
        if(artistaEncontrado.isPresent()){
            artistaEncontrado.get().getMusicas().forEach(m -> {
                System.out.println("Música:'" + m.getNomeMusica() + "' Albúm:'" + m.getAlbum() + "' Artista: '" + m.getArtista().getNome() + ":" + m.getArtista().getTipoArtista() + "'");
            });
        }else{
            System.out.println("Artista Não Cadastrado!");
        }



    }

    private void SobreArtista(){
        buscarArtistas();
        System.out.println("Digite o nome do artista para saber mais sobre:");
        System.out.print("R: ");
        var nomeArtistaProcurado = input.nextLine().toUpperCase();
        Optional<Artista> artistaEncontrado = artistaRepository.findByNome(nomeArtistaProcurado);
        if(artistaEncontrado.isPresent()){
            String respostaApi = ConsumoApiGemini.pesquisarSobreArtista(nomeArtistaProcurado);
            System.out.println(respostaApi);
        }else{
            System.out.println("Não encontrado!");
        }
    }

}

