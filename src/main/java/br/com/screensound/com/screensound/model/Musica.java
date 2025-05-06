package br.com.screensound.com.screensound.model;

import jakarta.persistence.*;

@Entity
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeMusica;
    private String album;

    @ManyToOne
    private Artista artista;

    public Musica(){}

    public Musica(String nomeMusica, String album) {
        this.nomeMusica = nomeMusica;
        this.album = album;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public String getAlbum() {
        return album;
    }

    public Artista getArtista() {
        return artista;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "nomeMusica='" + nomeMusica + '\'' +
                ", album='" + album + '\'' +
                ", artista=" + artista +
                '}';
    }
}
