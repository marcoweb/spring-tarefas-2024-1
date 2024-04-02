package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public Compromisso get(@PathVariable Long id) {
        return CompromissoRepo.findById(id).get();
    }

    @PostMapping("/{id}")
    public Compromisso put(@RequestBody Compromisso Compromisso, @PathVariable Long id) {
        Compromisso resultado = CompromissoRepo.findById(id).get();
        resultado.setDescricao(Compromisso.getDescricao());
        return CompromissoRepo.save(Compromisso);
    }
}
