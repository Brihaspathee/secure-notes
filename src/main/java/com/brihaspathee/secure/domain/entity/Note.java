package com.brihaspathee.secure.domain.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 13, January 2025
 * Time: 5:10 PM
 * Project: secure-notes
 * Package Name: com.brihaspathee.secure.domain.entity
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;

    private String ownerUsername;
}
