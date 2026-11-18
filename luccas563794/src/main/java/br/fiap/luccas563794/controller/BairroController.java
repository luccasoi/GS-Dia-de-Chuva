package br.fiap.luccas563794.controller;

import br.fiap.luccas563794.dto.BairroDTO;
import br.fiap.luccas563794.service.BairroService;
import br.fiap.luccas563794.service.NaoEncontradoException;
import br.fiap.luccas563794.service.PainelService;
import br.fiap.luccas563794.service.RegraNegocioException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bairros")
@RequiredArgsConstructor
public class BairroController {
    private final BairroService service;
    private final PainelService painelService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("bairros", service.listar());
        return "bairros/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("bairro", new BairroDTO());
        model.addAttribute("zonas", zonas());
        return "bairros/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("bairro", service.buscarPorId(id));
        model.addAttribute("zonas", zonas());
        return "bairros/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("bairro") BairroDTO dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("zonas", zonas());
            return "bairros/form";
        }
        service.salvar(dto);
        return "redirect:/bairros";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, Model model) {
        try {
            service.excluir(id);
            return "redirect:/bairros";
        }
        catch (RegraNegocioException e) {
            model.addAttribute("erro", e.getMessage());
        }
        catch (NaoEncontradoException e) {
            model.addAttribute("erro", e.getMessage());
        }
        model.addAttribute("bairros", service.listar());
        return "bairros/lista";
    }

    @GetMapping("/{id}/painel")
    public String painel(@PathVariable Long id, Model model) {
        model.addAttribute("painel", painelService.montarPainelCompleto(id));
        return "bairros/painel";
    }

    private List<String> zonas() {
        List<String> zonas = new ArrayList<>();
        zonas.add("Centro");
        zonas.add("Norte");
        zonas.add("Sul");
        zonas.add("Leste");
        zonas.add("Oeste");
        return zonas;
    }
}
