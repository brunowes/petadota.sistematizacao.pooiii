package com.example.petadota.service;

import com.example.petadota.model.Animal;
import com.example.petadota.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    public List<Animal> findAll() {
        return repository.findAll();
    }

    public Optional<Animal> findById(Long id) {
        return repository.findById(id);
    }

    public Animal save(Animal animal) {
        return repository.save(animal);
    }

    public Animal update(Long id, Animal animalDetails) {
        Optional<Animal> optionalAnimal = repository.findById(id);
        if (optionalAnimal.isPresent()) {
            Animal animal = optionalAnimal.get();
            animal.setNome(animalDetails.getNome());
            animal.setTipo(animalDetails.getTipo());
            animal.setIdade(animalDetails.getIdade());
            animal.setRaca(animalDetails.getRaca());
            animal.setStatusAdocao(animalDetails.getStatusAdocao());
            animal.setDescricao(animalDetails.getDescricao());
            return repository.save(animal);
        } else {
            throw new RuntimeException("Animal não encontrado com o ID: " + id);
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
