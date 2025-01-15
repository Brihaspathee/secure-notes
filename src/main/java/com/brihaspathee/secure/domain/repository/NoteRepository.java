package com.brihaspathee.secure.domain.repository;

import com.brihaspathee.secure.domain.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 13, January 2025
 * Time: 5:16 PM
 * Project: secure-notes
 * Package Name: com.brihaspathee.secure.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByOwnerUsername(String username);
}
