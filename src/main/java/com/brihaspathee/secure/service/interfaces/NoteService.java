package com.brihaspathee.secure.service.interfaces;

import com.brihaspathee.secure.domain.entity.Note;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 13, January 2025
 * Time: 7:21 PM
 * Project: secure-notes
 * Package Name: com.brihaspathee.secure.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface NoteService {

    Note save(Note note);

    void update(Note note);

    void delete(Long id);

    List<Note> findByUserName(String username);
}
