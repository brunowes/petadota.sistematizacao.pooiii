package com.example.petadota.controller;

import com.example.petadota.model.Animal;
import com.example.petadota.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @Operation(summary = "Lista todos os animais disponíveis para adoção")
    @ApiResponse(responseCode = "200", description = "Lista de animais retornada com sucesso")
    @GetMapping
    public List<Animal> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Busca um animal por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Animal não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Animal> findById(@PathVariable Long id) {
        Optional<Animal> animal = service.findById(id);
        return animal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Adiciona um novo animal para adoção")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Animal criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<Animal> save(@Valid @RequestBody Animal animal) {
        Animal savedAnimal = service.save(animal);
        return new ResponseEntity<>(savedAnimal, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza as informações de um animal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Animal não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Animal> update(@PathVariable Long id, @RequestBody Animal animalDetails) {
        try {
            Animal updatedAnimal = service.update(id, animalDetails);
            return ResponseEntity.ok(updatedAnimal);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Exclui um animal pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Animal excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Animal não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
