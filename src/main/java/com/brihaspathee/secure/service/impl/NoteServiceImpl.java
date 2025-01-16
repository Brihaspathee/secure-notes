package com.brihaspathee.secure.service.impl;

import com.brihaspathee.secure.domain.entity.Note;
import com.brihaspathee.secure.domain.repository.NoteRepository;
import com.brihaspathee.secure.service.interfaces.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 13, January 2025
 * Time: 7:22 PM
 * Project: secure-notes
 * Package Name: com.brihaspathee.secure.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public Note save(Note note) {
        Note savedNote = noteRepository.save(note);
        log.info("Note saved: {}", savedNote);
        return savedNote;
    }

    @Override
    public void update(Note note) {
        Note noteToUpdate = noteRepository.findById(note.getId()).orElseThrow(() ->  new RuntimeException("Note not found"));
        noteToUpdate.setContent(note.getContent());
        noteRepository.save(noteToUpdate);

    }

    @Override
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public List<Note> findByUserName(String username) {
        return noteRepository.findByOwnerUsername(username);
    }
}
