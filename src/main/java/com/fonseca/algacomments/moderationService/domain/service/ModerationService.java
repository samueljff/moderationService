package com.fonseca.algacomments.moderationService.domain.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ModerationService {
    private static final List<String> BANNED_WORDS = List.of("ódio", "xingamento", "php");

    public com.fonseca.algacomments.moderationService.api.model.ModerationOutput moderate(com.fonseca.algacomments.moderationService.api.model.ModerationInput request) {
        String text = request.getText().toLowerCase();

        Set<String> bannedWordsFound = new HashSet<>();
        for (String word : BANNED_WORDS) {
            if (text.contains(word.toLowerCase())) {
                bannedWordsFound.add(word);
            }
        }

        if (!bannedWordsFound.isEmpty()) {
            return com.fonseca.algacomments.moderationService.api.model.ModerationOutput.builder()
                    .approved(false)
                    .reason("Contém palavras proibidas: " + bannedWordsFound)
                    .build();
        }

        return com.fonseca.algacomments.moderationService.api.model.ModerationOutput.builder()
                .approved(true)
                .reason("Comentário aprovado não palavras proibidas")
                .build();
    }
}
