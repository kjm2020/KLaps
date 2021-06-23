package com.kds.cop.klap.common;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * GenericController - 컨트롤러의 공통 기능 추상화(CRUD)
 *
 *
 */
@Slf4j
public abstract class CommonController<T ,ID> {

    @Autowired
    private JpaRepository<T, ID> repository;

    @GetMapping("/{id}")
    public T select(@PathVariable ID id) {
        log.debug("GenericController.select - {}",id);
        return repository.findById(id).get();
    }

    @GetMapping
    public List<T> list(){
        log.debug("GenericController.list");
        return repository.findAll();
    }

    @PostMapping
    public T create(@RequestBody T t) {
        log.debug("GenericController.create - {}",t.toString());
        T created = repository.save(t);
        return created;
    }

    @PutMapping("/{id}")
    public T update(@RequestBody T t) {
        log.debug("GenericController.update - {}",t.toString());
        T updated = repository.save(t);
        return updated;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable ID id) {
        log.debug("GenericController.delete - {}",id);
        repository.deleteById(id);
        return true;
    }
}


