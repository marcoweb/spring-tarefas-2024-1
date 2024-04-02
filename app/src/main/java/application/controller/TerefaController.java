package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.model.Tarefa;
import application.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefas")
public class TerefaController {
    @Autowired
    private TarefaRepository tarefaRepo;

    @PostMapping
    public Tarefa post(@RequestBody Tarefa tarefa)  {
        return tarefaRepo.save(tarefa);
    }

    @PutMapping("/{id}")
    public Tarefa put(@RequestBody Tarefa tarefa, @PathVariable Long id)  {
        Tarefa resultado = tarefaRepo.findById(id).get();
        resultado.setDescricao(tarefa.getDescricao());
        resultado.setConcluido(tarefa.getConcluido());
        return tarefaRepo.save(resultado);
    }
    @PatchMapping("/{id}")
    public Tarefa patch(@RequestBody Tarefa tarefa, @PathVariable Long id)  {
        Tarefa resultado = tarefaRepo.findById(id).get();
        if(tarefa.getDescricao() != null) {
            resultado.setDescricao(tarefa.getDescricao());
        }
        resultado.setConcluido(tarefa.getConcluido());
        return tarefaRepo.save(resultado);
    }

    @GetMapping
    public List<Tarefa> getAll() {
        return (List<Tarefa>) tarefaRepo.findAll();
    }

    @GetMapping("/{id}")
    public Tarefa get(@PathVariable Long id) {
        return tarefaRepo.findById(id).get();
    }
}
