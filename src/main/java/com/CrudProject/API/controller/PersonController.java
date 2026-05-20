package com.CrudProject.API.controller;

import com.CrudProject.API.dto.ApiResponse;
import com.CrudProject.API.dto.PersonDto;
import com.CrudProject.API.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public List<PersonDto> getAllPersons() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public PersonDto getPersonById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public PersonDto updatePerson(
            @PathVariable Long id,
            @RequestBody PersonDto personDto
    ) {
        return personService.update(id, personDto);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<PersonDto>> savePersonApiResponse(@RequestBody @Valid PersonDto personDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.saveApiResponse(personDto));
    }

}