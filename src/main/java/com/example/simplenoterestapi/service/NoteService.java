package com.example.simplenoterestapi.service;

import com.example.simplenoterestapi.dto.NoteRequest;
import com.example.simplenoterestapi.exception.ResourceNotFoundException;
import com.example.simplenoterestapi.exception.UnauthorizedResourceExeception;
import com.example.simplenoterestapi.model.Note;
import com.example.simplenoterestapi.model.User;
import com.example.simplenoterestapi.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserService userService;

    public List<Note> findAllNotesByUser() {
        User authUser = userService.getAuthenticatedUser();
        return noteRepository.findAllByUser(authUser)
                .orElseThrow(() -> new ResourceNotFoundException("Notes", "user", authUser.getEmail()));
    }

    public Note findOneNote(Long id) {
        User authUser = userService.getAuthenticatedUser();
        Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
        if (authUser.getId() != note.getUser().getId()) {
            throw new UnauthorizedResourceExeception("Note");
        }
        return note;
    }

    public void createNote(NoteRequest noteRequest) {
        User authUser = userService.getAuthenticatedUser();
        Note newNote = Note.builder()
                .title(noteRequest.title())
                .content(noteRequest.content())
                .user(authUser)
                .build();
        noteRepository.save(newNote);

    }

    public boolean updateNote(Long id, NoteRequest noteRequest) {
        Note note = findOneNote(id);
        note.setTitle(noteRequest.title());
        note.setContent(noteRequest.content());
        if (noteRepository.save(note) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteNoteById(Long id) {
        // Serve as a step to check if user is authorized to delete this resource
        Note note = findOneNote(id);
        noteRepository.deleteById(id);
    }
}
