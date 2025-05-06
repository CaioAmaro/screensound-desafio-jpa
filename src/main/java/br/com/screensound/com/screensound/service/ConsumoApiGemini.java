package br.com.screensound.com.screensound.service;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class ConsumoApiGemini {

    public static String pesquisarSobreArtista(String texto) {
        final String KEY_API_GEMINI = "AIzaSyCB7xQfA_Ip6ndBjKEOtGIb0wjXA64cgow";

        ChatModel gemini = GoogleAiGeminiChatModel.builder()
                .apiKey(KEY_API_GEMINI)
                .modelName("gemini-1.5-flash")
                .build();

        String response = gemini.chat("Fale brevemente sobre o artista(SOLO,DUPLA,BANDA) na industria da musica, max 100 caracteres: " + texto  );
        return response.trim();
    }
}
