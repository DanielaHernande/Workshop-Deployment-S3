package com.riwi.QuickNote.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.QuickNote.domain.entities.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
};