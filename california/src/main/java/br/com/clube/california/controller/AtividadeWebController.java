package br.com.clube.california.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.clube.california.repository.AtividadesRepository;
import br.com.clube.california.model.Atividades;

@Controller
@RequestMapping("/web/atividades")
public class AtividadeWebController {

    @Autowired
    private AtividadesRepository repositorio;

    @GetMapping
    public String listar(Model model){
        List<Atividades> atividades = repositorio.findAll();
        model.addAttribute("atividade", atividades);
        return "atividades/list";
    }
    
    @GetMapping(value = "/{id}")
    public String listarId(@PathVariable Long id, Model model){
        Atividades atividade = repositorio.findById(id).orElse(null);
        model.addAttribute("atividades", atividade);
        return "atividades/detail";
    }

    @GetMapping("/novo")
    public String novaAtividadeForm(Model model) {
        model.addAttribute("atividades", new Atividades());
        return "atividades/form";
    }

    @PostMapping
    public String adicionar(Atividades atividade) {
        repositorio.save(atividade);
        return "redirect:/web/atividades";
    }

        @GetMapping("/editar/{id}")
    public String editarAtividadeForm(@PathVariable Long id, Model model) {
        Atividades atividade = repositorio.findById(id).orElse(null);
        if (atividade != null) {
            model.addAttribute("atividades", atividade);
            return "atividades/edit";
        }
        return "redirect:/web/atividades";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarAtividade(@PathVariable Long id, Atividades atividadeAtualizada) {
        Atividades atividade = repositorio.findById(id).orElse(null);
        if (atividade != null) {
            atividade.setNome(atividadeAtualizada.getNome());
            atividade.setDesricao(atividadeAtualizada.getDesricao());
            repositorio.save(atividade);
        }
        return "redirect:/web/atividades";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAtividade(@PathVariable Long id) {
        Atividades atividade = repositorio.findById(id).orElse(null);
        if (atividade != null) {
            repositorio.delete(atividade);
        }
        return "redirect:/web/atividades";
    }
}