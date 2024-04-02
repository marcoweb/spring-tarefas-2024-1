package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.Compromisso;

public interface CompromissoRepository extends CrudRepository<Compromisso, Long> {
    
}

