package com.riwi.QuickNote.api.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.QuickNote.api.dto.request.NoteReq;
import com.riwi.QuickNote.api.dto.response.NoteResp;
import com.riwi.QuickNote.infrastructure.abstract_services.INoteService;
import com.riwi.QuickNote.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/notes")
public class NoteController {

    @Autowired
    private final INoteService noteService;

    // Find All
    @GetMapping
    @Operation(summary = "List all Notes",
                description = "Returns a paginated list of all notes.")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation. Returns the paginated list of notes."),
            @ApiResponse(responseCode = "400", description = "Bad request. This may occured if the parameters are incorrect.")
    })
    public ResponseEntity<Page<NoteResp>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestHeader(required = false) SortType sortType) {
        
        if (Objects.isNull(sortType)) sortType = SortType.NONE;

        return ResponseEntity.ok(this.noteService.getAll(page - 1, size, sortType));
    };

    // Obtner por id
    @GetMapping("/{id}")
    @Operation(summary = "Find note by ID",
               description = "Returns the note with the specified ID.")
               
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation. Returns the note."),
            @ApiResponse(responseCode = "404", description = "Note not found with the specified ID.")
    })
    public ResponseEntity<NoteResp> get(@PathVariable Long id) {

        return ResponseEntity.ok(this.noteService.get(id));
    };

    // Create
    @PostMapping
    @Operation(summary = "Create a new note",
               description = "Creates a new note with the provided details.")

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Note created successfully."),
        @ApiResponse(responseCode = "400", description = "Invalid request. The provided data is incorrect."),
        @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    public ResponseEntity<NoteResp> create(
            @Validated @RequestBody NoteReq request) {

        return ResponseEntity.ok(this.noteService.create(request));
    };

    // Update
    @PutMapping(path = "/{id}")
    @Operation(summary = "Update an existing note",
               description = "Updates the details of an existing note with the specified ID.")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Note updated successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid request. The provided data is incorrect."),
            @ApiResponse(responseCode = "404", description = "Note not found with the specified ID."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    public ResponseEntity<NoteResp> update(
            @Validated @RequestBody NoteReq request,
            @PathVariable Long id) {

        return ResponseEntity.ok(this.noteService.update(request, id));
    };

    // Delete
    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a note",
               description = "Deletes the note with the specified ID.")
               
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Note deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Note not found with the specified ID."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        this.noteService.delete(id);
        return ResponseEntity.noContent().build();
    };
};