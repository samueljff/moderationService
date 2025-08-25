package com.fonseca.algacomments.moderationService.domain.service;

import com.fonseca.algacomments.moderationService.api.model.ModerationInput;
import com.fonseca.algacomments.moderationService.api.model.ModerationOutput;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ModerationService {

    private final OrganizedModerationService organizedModerationService;

    public ModerationService(OrganizedModerationService organizedModerationService) {
        this.organizedModerationService = organizedModerationService;
    }

    public boolean isCommentAllowed(String comment) {
        return !organizedModerationService.containsProhibitedWord(comment);
    }

    public ModerationOutput moderate(ModerationInput request) {
        String text = request.getText().toLowerCase();

        if (organizedModerationService.containsProhibitedWord(text)) {
            var prohibitedWords = organizedModerationService.findProhibitedWords(text);
            return ModerationOutput.builder()
                    .approved(false)
                    .reason("Contém palavras proibidas: " + prohibitedWords)
                    .build();
        }

        return ModerationOutput.builder()
                .approved(true)
                .reason("Comentário aprovado, não contém palavras proibidas")
                .build();
    }
}
