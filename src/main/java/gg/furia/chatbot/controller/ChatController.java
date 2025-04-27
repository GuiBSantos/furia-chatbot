package gg.furia.chatbot.controller;

import gg.furia.chatbot.dto.ChatRequest;
import gg.furia.chatbot.dto.ChatResponse;
import gg.furia.chatbot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    @PostMapping("/chat")
    public Mono<ChatResponse> chat(@RequestBody ChatRequest request) {
        System.out.println("Recebendo mensagem: " + request.getMessage());

        return chatService.gerarResposta(request.getMessage())
                .map(resposta -> {
                    System.out.println("Resposta gerada: " + resposta);
                    return new ChatResponse(resposta);
                })
                .doOnError(e -> {
                    System.err.println("Erro ao gerar resposta: " + e.getMessage());
                });
    }
}
