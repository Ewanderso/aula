package br.com.clube.california.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.clube.california.model.Atividades;
import br.com.clube.california.repository.AtividadesRepository;

@RequestMapping("/atividades")
@RestController

public class AtividadesController<Lond> {
    
    @Autowired
    private AtividadesRepository repositorio;

    @GetMapping
    public List<Atividades> listar(){
    return repositorio.findAll();
    }

    @PostMapping
    // Variavel do tipo (socio) da classe (Socio)
    public Atividades adicionar(@RequestBody Atividades atividades){
        return repositorio.save(atividades);
    }

    @DeleteMapping(value = "/{id}")
    public String excluir(@PathVariable java.lang.Long id){
        Atividades atividadeExistente=repositorio.findById(id).orElse(null);
        if(atividadeExistente != null){
            repositorio.delete(atividadeExistente);
            return "Atividade excluida com sucesso";
        }
        return "Atividade não localizado";
    }

    @PutMapping(value = "/{id}")
    public Atividades alterar(@PathVariable long id, @RequestBody Atividades atividades) {
        Atividades atividadesExistente = repositorio.findById(id).orElse(null);
        if (atividadesExistente != null) {
            atividadesExistente.setNome(atividades.getNome());
            atividadesExistente.setAtividade(atividades.getAtividade());

            return repositorio.save(atividadesExistente);
            
        }
        return atividadesExistente;
    }
}
