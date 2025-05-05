package br.com.screensound.com.screensound.model;

public class Musica {
    private String nomeMusica;
    private String album;
    private Artista artista;

    public Musica(String nomeMusica, String album) {
        this.nomeMusica = nomeMusica;
        this.album = album;
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
