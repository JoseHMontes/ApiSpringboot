package com.CrudProject.API.service;
import com.CrudProject.API.dto.ApiResponse;
import com.CrudProject.API.dto.PersonDto;
import com.CrudProject.API.exceptions.PasswordException;
import com.CrudProject.API.mappers.PersonMapper;
import com.CrudProject.API.models.Person;
import com.CrudProject.API.repository.IPerson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {

    private final IPerson personRepository;
    private final PersonMapper personMapper;

    public PersonService(IPerson personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public PersonDto save(PersonDto personDto) {
        if (personDto.getPassword().length() < 8)
            throw new PasswordException("Password must be at least 8 characters long");
        if (!personDto.getEmail().contains("@"))
            throw new IllegalArgumentException("Invalid email format");

        Person person = personMapper.personDtoToPerson(personDto);

        Person savedPerson = personRepository.save(person);

        return personMapper.personToPersonDto(savedPerson);
    }

    public List<PersonDto> findAll() {

        return personRepository.findAll()
                .stream()
                .map(personMapper::personToPersonDto)
                .toList();
    }

    public PersonDto findById(Long id) {

        Person person = personRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Person not found with id: " + id));

        return personMapper.personToPersonDto(person);
    }

    public void delete(Long id) {

        personRepository.deleteById(id);

    }

    public PersonDto update(Long id, PersonDto personDto) {

        Person update = personRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Person not found with id: " + id));

        personMapper.updatePersonFromDto(personDto, update);

        Person updatedPerson = personRepository.save(update);

        return personMapper.personToPersonDto(updatedPerson);
    }

    public ApiResponse<PersonDto> saveApiResponse(PersonDto personDto) {
        if (personDto.getPassword().length() < 8)
            throw new PasswordException("Password must be at least 8 characters long");
        if (!personDto.getEmail().contains("@"))
            throw new IllegalArgumentException("Invalid email format");

        Person person = personMapper.personDtoToPerson(personDto);

        Person savedPerson = personRepository.save(person);

        return ApiResponse.<PersonDto>builder()
                .status(HttpStatus.CREATED)
                .body(personMapper.personToPersonDto(savedPerson))
                .build();
    }
}