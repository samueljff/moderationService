package com.fonseca.algacomments.moderationService.api.controller;

import com.fonseca.algacomments.moderationService.ModerationServiceApplication;
import com.fonseca.algacomments.moderationService.api.model.ModerationInput;
import com.fonseca.algacomments.moderationService.api.model.ModerationOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moderate")
@RequiredArgsConstructor
public class ModerationController {
    private final ModerationServiceApplication moderationService;

    @PostMapping
    public ModerationOutput moderate(@RequestBody ModerationInput request) {
        return moderationService.moderate(request);
    }
}