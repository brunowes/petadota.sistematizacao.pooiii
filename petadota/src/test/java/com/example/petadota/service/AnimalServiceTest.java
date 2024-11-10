package com.example.petadota.service;
import com.example.petadota.model.Animal;
import com.example.petadota.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnimalServiceTest {

    @Autowired
    private AnimalService service;

    @Autowired
    private AnimalRepository repository;

    private Animal animal;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();

        animal = new Animal();
        animal.setNome("Rex");
        animal.setTipo("Cachorro");
        animal.setIdade(3);
        animal.setRaca("Labrador");
        animal.setStatusAdocao("Disponível");
        animal.setDescricao("Amigável e brincalhão");
        animal = repository.save(animal);
    }

    @Test
    public void testFindAll() {
        assertFalse(service.findAll().isEmpty(), "A lista de animais não deveria estar vazia");
    }

    @Test
    public void testFindById() {
        Optional<Animal> foundAnimal = service.findById(animal.getId());
        assertTrue(foundAnimal.isPresent(), "O animal deveria estar presente no banco de dados");
    }

    @Test
    public void testSave() {
        Animal novoAnimal = new Animal();
        novoAnimal.setNome("Garfield");
        novoAnimal.setTipo("Gato");
        novoAnimal.setIdade(2);
        novoAnimal.setRaca("Laranja");
        novoAnimal.setStatusAdocao("Disponível");
        novoAnimal.setDescricao("Muito dócil");

        Animal savedAnimal = service.save(novoAnimal);
        assertNotNull(savedAnimal.getId(), "O ID do animal salvo não deveria ser nulo");
    }

    @Test
    public void testUpdate() {
        animal.setStatusAdocao("Adotado");
        Animal updatedAnimal = service.update(animal.getId(), animal);
        assertEquals("Adotado", updatedAnimal.getStatusAdocao(), "O status do animal deveria ser 'Adotado'");
    }

    @Test
    public void testDeleteById() {
        service.deleteById(animal.getId());
        Optional<Animal> deletedAnimal = service.findById(animal.getId());
        assertFalse(deletedAnimal.isPresent(), "O animal deveria ter sido excluído");
    }
}