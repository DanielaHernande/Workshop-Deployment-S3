package com.riwi.QuickNote.infrastructure.abstract_services;

import com.riwi.QuickNote.api.dto.request.NoteReq;
import com.riwi.QuickNote.api.dto.response.NoteResp;

public interface INoteService extends CrudServices<NoteResp, NoteReq, Long>{
    
    public String FIELD_BY_SORT = "dateCreation";
};