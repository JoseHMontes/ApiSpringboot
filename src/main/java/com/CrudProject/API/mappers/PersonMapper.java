package com.CrudProject.API.mappers;
import com.CrudProject.API.dto.PersonDto;
import com.CrudProject.API.models.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDto personToPersonDto(Person person);

    Person personDtoToPerson(PersonDto personDto);

    @Mapping(target = "id", ignore = true)
    void updatePersonFromDto(PersonDto dto, @MappingTarget Person entity);

}