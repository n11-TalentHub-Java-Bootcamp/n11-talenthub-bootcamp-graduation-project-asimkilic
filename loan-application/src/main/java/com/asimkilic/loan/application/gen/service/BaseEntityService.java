package com.asimkilic.loan.application.gen.service;

import com.asimkilic.loan.application.gen.entity.BaseEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity, D extends JpaRepository> {

    private D repository;

    public List<E> findAll() {
        return repository.findAll();
    }

    public Optional<E> findById(String id) {
        return repository.findById(id);
    }

    public E save(E entity) {
        return (E) repository.save(entity);
    }

    public void delete(E entity) {
        repository.delete(entity);
    }

    public D getRepository() {
        return repository;
    }

}
