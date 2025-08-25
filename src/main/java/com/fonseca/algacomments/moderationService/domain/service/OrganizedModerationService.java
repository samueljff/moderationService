package com.fonseca.algacomments.moderationService.domain.service;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class OrganizedModerationService {

    // Organizar por categorias para melhor manutenção
    private static final Set<String> PROFANITY = Set.of(
            "porra", "merda", "caralho", "buceta", "puta", "cu", "cacete",
            "p0rra", "m3rda", "c@ralho" // variações básicas
    );

    private static final Set<String> INSULTS = Set.of(
            "idiota", "burro", "estúpido", "imbecil", "otário", "babaca",
            "lixo", "nojento", "corno", "desgraçado", "vagabundo", "safado"
    );

    private static final Set<String> HATE_SPEECH = Set.of(
            "ódio", "desgraça", "viado", "gay", "bicha", "traveco",
            "macaco", "negro", "preto", "retardado"
    );

    private static final Set<String> EXPRESSIONS = Set.of(
            "fdp", "pqp", "vsf", "vai se foder", "se foda", "foda-se",
            "vai tomar no cu", "tomar no cu", "vtnc"
    );

    private static final Set<String> VIOLENCE = Set.of(
            "matar", "morrer", "suicida", "mata", "morre", "assassino"
    );

    // Combinar todas as listas
    private static final Set<String> ALL_PROHIBITED_WORDS;

    static {
        ALL_PROHIBITED_WORDS = new HashSet<>();
        ALL_PROHIBITED_WORDS.addAll(PROFANITY);
        ALL_PROHIBITED_WORDS.addAll(INSULTS);
        ALL_PROHIBITED_WORDS.addAll(HATE_SPEECH);
        ALL_PROHIBITED_WORDS.addAll(EXPRESSIONS);
        ALL_PROHIBITED_WORDS.addAll(VIOLENCE);
    }

    public boolean containsProhibitedWord(String text) {
        return ALL_PROHIBITED_WORDS.stream()
                .anyMatch(word -> text.toLowerCase().contains(word));
    }

    //método para retornar as palavras detectadas
    public Set<String> findProhibitedWords(String text) {
        String lower = text.toLowerCase();
        Set<String> found = new HashSet<>();
        for (String word : ALL_PROHIBITED_WORDS) {
            if (lower.contains(word)) {
                found.add(word);
            }
        }
        return found;
    }
}