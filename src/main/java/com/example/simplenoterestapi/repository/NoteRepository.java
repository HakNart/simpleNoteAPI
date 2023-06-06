package com.example.simplenoterestapi.repository;

import com.example.simplenoterestapi.model.Note;
import com.example.simplenoterestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("""
    select n from Note n where n.user = :user order by n.id desc 
""")
    Optional<List<Note>> findAllByUser(User user);
}
