package com.riwi.QuickNote.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.QuickNote.api.dto.request.NoteReq;
import com.riwi.QuickNote.api.dto.response.NoteResp;
import com.riwi.QuickNote.domain.entities.Note;
import com.riwi.QuickNote.domain.repositories.NoteRepository;
import com.riwi.QuickNote.infrastructure.abstract_services.INoteService;
import com.riwi.QuickNote.infrastructure.helpers.mappers.NoteMapper;
import com.riwi.QuickNote.utils.enums.SortType;
import com.riwi.QuickNote.utils.exceptions.BadIdException;
import com.riwi.QuickNote.utils.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NoteService implements INoteService{

    @Autowired
    private final NoteRepository noteRepository;

    @Autowired
    private final NoteMapper noteMapper;

    // Create
    @Override
    public NoteResp create(NoteReq request) {
        
        Note note = noteMapper.toEntity(request);
        
        return noteMapper.toEntityResponse(this.noteRepository.save(note));
    };

    @Override
    public NoteResp get(Long id) {

        Note note = noteRepository.findById(id)
                        .orElseThrow(() -> new BadIdException("Note not found with ID:" + id));

        return noteMapper.toEntityResponse(note);
    };
    
    // Update
    @SuppressWarnings("unused")
    @Override
    public NoteResp update(NoteReq request, Long id) {

        Note note = find(id);
    
        Note noteUpdate = this.noteMapper.toEntity(request);

        noteUpdate.setId(id);

        return this.noteMapper.toEntityResponse(this.noteRepository.save(noteUpdate));
    };

    // Delete
    @Override
    public void delete(Long id) {

        Note note = this.find(id);

        this.noteRepository.delete(note);
    };

    // Find All
    @SuppressWarnings("null")
    @Override
    public Page<NoteResp> getAll(int page, int size, SortType sortType) {

        if (page < 0) page = 0;

        PageRequest pagination = null;

        switch (sortType) {

            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        };

        return noteRepository.findAll(pagination).map(noteMapper::toEntityResponse);
    };

    // Buscar por id
    private Note find(Long id) {

        return this.noteRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("There are no notes with this id"));
    }

};