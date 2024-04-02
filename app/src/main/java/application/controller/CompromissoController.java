package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.model.Compromisso;
import application.repository.CompromissoRepository;

@RestController
@RequestMapping("/compromissos")
public class CompromissoController {
    @Autowired
    private CompromissoRepository CompromissoRepo;

    @GetMapping
    public List<Compromisso> getAll() {
        return (List<Compromisso>) CompromissoRepo.findAll();
    }

    @PostMapping
    public Compromisso post(@RequestBody Compromisso Compromisso) {
        return CompromissoRepo.save(Compromisso);
    }
}
