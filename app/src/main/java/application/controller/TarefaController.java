package application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Tarefa;
import application.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepo;

    @PostMapping
    public Tarefa post(@RequestBody Tarefa tarefa)  {
        if(tarefa.getDescricao() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "O valor do campo 'descricao' não pode ser nulo");
        }
        return tarefaRepo.save(tarefa);
    }

    @PutMapping("/{id}")
    public Tarefa put(@RequestBody Tarefa tarefa, @PathVariable Long id)  {
        Optional<Tarefa> resultado = tarefaRepo.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }
        if(tarefa.getDescricao() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "O valor do campo 'descricao' não pode ser nulo");
        }
        resultado.get().setDescricao(tarefa.getDescricao());
        resultado.get().setConcluido(tarefa.getConcluido());
        return tarefaRepo.save(resultado.get());
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
        Optional<Tarefa> resultado = tarefaRepo.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }
        return resultado.get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if(tarefaRepo.existsById(id)) {
            tarefaRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }
        
    }
}
