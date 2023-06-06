package com.example.simplenoterestapi.controller;

import com.example.simplenoterestapi.dto.ApiResponse;
import com.example.simplenoterestapi.dto.NoteRequest;
import com.example.simplenoterestapi.model.Note;
import com.example.simplenoterestapi.model.User;
import com.example.simplenoterestapi.repository.NoteRepository;
import com.example.simplenoterestapi.service.NoteService;
import com.example.simplenoterestapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
public class NoteController {
    private final NoteRepository noteRepository;
    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteRepository noteRepository, NoteService noteService, UserService userService) {
        this.noteRepository = noteRepository;
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping(value = "/users/notes")
    public List<Note> getAllNoteByUser() {
        return noteService.findAllNotesByUser();
    }

    @PostMapping(value = "/users/notes")
    public ResponseEntity<?> createNewNote(@RequestBody NoteRequest noteRequest) {
        noteService.createNote(noteRequest);
        return ResponseEntity.ok().body(new ApiResponse(true, "Successfully created new note"));
    }
    @GetMapping(value = "/users/notes/{id}")
    public ResponseEntity<?> getOneNote(@PathVariable Long id) {
        Note note;
        try {
            note = noteService.findOneNote(id);
        } catch (Exception e) {
            log.error("Exception occurred", e);
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(note);
    }

    @PutMapping(value = "/users/notes/{id}")
    public ResponseEntity<?> updateNote(@PathVariable Long id, @RequestBody NoteRequest noteRequest) {
        noteService.updateNote(id, noteRequest);
        return ResponseEntity.ok().body(new ApiResponse(true, "Successfully updated note"));
    }

    @DeleteMapping(value = "/users/notes/{id}")
    public ResponseEntity<?> deleteOneNote(@PathVariable Long id) {
        noteService.deleteNoteById(id);
        return ResponseEntity.ok().body(new ApiResponse(true, "Successfully deleted note"));
    }
}
