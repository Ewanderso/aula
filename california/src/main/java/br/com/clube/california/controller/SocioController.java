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

import br.com.clube.california.repository.SocioRepository;
import br.com.clube.california.model.Socio;

@RequestMapping("/socio")
@RestController
public class SocioController<Lond> {
    @Autowired
    private SocioRepository repositorio;

    @GetMapping
    public List<Socio> listar(){
    return repositorio.findAll();
    }

    @PostMapping
    // Variavel do tipo (socio) da classe (Socio)
    public Socio adicionar(@RequestBody Socio socio){
        return repositorio.save(socio);
    }

    @DeleteMapping(value = "/{id}")
    public String excluir(@PathVariable Long id){
        Socio socioExistente=repositorio.findById(id).orElse(null);
        if(socioExistente != null){
            repositorio.delete(socioExistente);
            return "sócio excluido com sucesso";
        }
        return "Sócio não localizado";
    }

    @PutMapping(value = "/{id}")
    public Socio alterar(@PathVariable Long id, @RequestBody Socio socio) {
        Socio socioExistente = repositorio.findById(id).orElse(null);

        if (socioExistente != null) {
            socioExistente.setNome(socio.getNome());
            socioExistente.setEndereco(socio.getEndereco());
            socioExistente.setCpf(socio.getCpf());
            socioExistente.setEmail(socio.getEmail());
            socioExistente.setTelefone(socio.getTelefone());

            return repositorio.save(socioExistente);
            
        }
        return socioExistente;
    }
}
