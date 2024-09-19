package com.riwi.QuickNote.infrastructure.helpers.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.riwi.QuickNote.api.dto.request.NoteReq;
import com.riwi.QuickNote.api.dto.response.NoteResp;
import com.riwi.QuickNote.domain.entities.Note;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NoteMapper extends GenericMapper<NoteReq, NoteResp, Note>{

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreation", ignore = true)
    @Mapping(target = "dateUpdate", ignore = true)
    Note toEntity(NoteReq request);
};