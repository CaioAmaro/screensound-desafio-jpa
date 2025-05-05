package br.com.screensound.com.screensound.model;

public enum TipoArtista {
    SOLO("Solo"),
    DUPLA("Dupla"),
    BANDA("Banda");

    private String tipoArtistaPortugues;

    TipoArtista(String tipoArtistaPortugues){
        this.tipoArtistaPortugues = tipoArtistaPortugues;
    }

    public static TipoArtista fromTipoArtistaPortugues(String text){
        for(TipoArtista tipoArtista: TipoArtista.values()){
            if(tipoArtista.tipoArtistaPortugues.equalsIgnoreCase(text)){
                return tipoArtista;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo de categoria de Artista Atendida");
    }
}
