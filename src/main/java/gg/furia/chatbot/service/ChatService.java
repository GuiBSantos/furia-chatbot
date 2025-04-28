package gg.furia.chatbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    private final WebClient webClient;
    private final String apiKey;

    public ChatService(@Value("${gemini.api.key}") String apiKey,
                       WebClient.Builder webClientBuilder) {
        this.apiKey = apiKey;
        this.webClient = webClientBuilder
                .baseUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent")
                .build();
    }

    public Mono<String> gerarResposta(String mensagemUsuario) {
         String prompt = """
        INSTRUÇÕES GLOBAIS:
        • Se for a primeira interação (saudação inicial), comece com uma frase de boas-vindas curta (ex.: "Olá, Furioso!").  
        • Em todas as respostas seguintes, **não** repita a saudação: seja direto, objetivo e profissional.  
        • Mantenha leveza e entusiasmo, use apenas 1–2 emojis por resposta quando fizer sentido.
        • Responda SEM formatação Markdown ou asteriscos, apenas texto plano.\s

        Usuário: %s
        """.formatted(mensagemUsuario);
        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.queryParam("key", apiKey).build())
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var candidates = (List<Map<String, Object>>) response.get("candidates");
                    if (candidates == null || candidates.isEmpty()) {
                        return "Desculpe, não consegui entender a resposta do servidor.";
                    }
                    var content = (Map<String, Object>) candidates.get(0).get("content");
                    var parts = (List<Map<String, Object>>) content.get("parts");
                    return parts != null && !parts.isEmpty() ? (String) parts.get(0).get("text") : "Desculpe, não consegui gerar uma resposta.";
                })
                .doOnError(e -> {
                    System.err.println("Erro ao gerar resposta: " + e.getMessage());
                });
    }
}
