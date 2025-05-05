package br.com.screensound.com.screensound.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoArtista tipoArtista;

    @Transient
    private List<Musica> musicas = new ArrayList<>();

    public Artista(){}

    public Artista(String nomeArtista, TipoArtista tipoArtista) {
        this.nome = nomeArtista.toUpperCase();
        this.tipoArtista = tipoArtista;
    }

    public String getNome() {
        return nome;
    }

    public TipoArtista getTipoArtista() {
        return tipoArtista;
    }

    public void setMusicas(Musica musica) {
        this.musicas.add(musica);
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "nome='" + nome + '\'' +
                ", tipoArtista=" + tipoArtista +
                '}';
    }
}
