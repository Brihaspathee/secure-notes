package com.brihaspathee.secure.controller;

import com.brihaspathee.secure.domain.entity.Note;
import com.brihaspathee.secure.service.interfaces.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 15, January 2025
 * Time: 5:28 AM
 * Project: secure-notes
 * Package Name: com.brihaspathee.secure.controller
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NotesController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note, @AuthenticationPrincipal UserDetails user) {
        log.info("user: {}", user.getUsername());
        note.setOwnerUsername(user.getUsername());
        Note savedNote = noteService.save(note);
        return ResponseEntity.ok().body(savedNote);
    }

    @GetMapping
    public  ResponseEntity<List<Note>> getUserNotes(@AuthenticationPrincipal UserDetails user) {
        log.info("user: {}", user.getUsername());
        List<Note> userNotes = noteService.findByUserName(user.getUsername());
        return ResponseEntity.ok().body(userNotes);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long noteId, @AuthenticationPrincipal UserDetails user) {
        noteService.delete(noteId);
        return ResponseEntity.noContent().build();
    }
}
