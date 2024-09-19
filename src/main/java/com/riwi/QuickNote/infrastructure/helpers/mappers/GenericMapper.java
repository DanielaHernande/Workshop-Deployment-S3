package com.riwi.QuickNote.infrastructure.helpers.mappers;

public interface GenericMapper <RequestDto, ResponseDto, Entity>{
    
    Entity toEntity(RequestDto userRequest);

    ResponseDto toEntityResponse(Entity userEntity);
};