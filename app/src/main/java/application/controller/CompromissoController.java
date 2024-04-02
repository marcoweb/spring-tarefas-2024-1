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
    private CompromissoRepository compromissoRepo;

    @GetMapping
    public List<Compromisso> getAll() {
        return (List<Compromisso>) compromissoRepo.findAll();
    }

    @PostMapping
    public Compromisso post(@RequestBody Compromisso compromisso) {
        return compromissoRepo.save(compromisso);
    }

    @GetMapping("/{id}")
    public Compromisso get(@PathVariable Long id) {
        return compromissoRepo.findById(id).get();
    }

    @PostMapping("/{id}")
    public Compromisso put(@RequestBody Compromisso compromisso, @PathVariable Long id) {
        Compromisso resultado = compromissoRepo.findById(id).get();
        resultado.setDescricao(compromisso.getDescricao());
        return compromissoRepo.save(compromisso);
    }
}
