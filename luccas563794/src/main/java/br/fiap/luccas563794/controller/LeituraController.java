package br.fiap.luccas563794.controller;

import br.fiap.luccas563794.dto.LeituraDTO;
import br.fiap.luccas563794.service.LeituraService;
import br.fiap.luccas563794.service.NaoEncontradoException;
import br.fiap.luccas563794.service.TrechoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/leituras")
@RequiredArgsConstructor
public class LeituraController {
    private final LeituraService service;
    private final TrechoService trechoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("leituras", service.listar());
        return "leituras/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("leitura", new LeituraDTO());
        model.addAttribute("trechos", trechoService.listar());
        return "leituras/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("leitura", service.buscarPorId(id));
        model.addAttribute("trechos", trechoService.listar());
        return "leituras/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("leitura") LeituraDTO dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("trechos", trechoService.listar());
            return "leituras/form";
        }
        service.salvar(dto);
        return "redirect:/leituras";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, Model model) {
        try {
            service.excluir(id);
            return "redirect:/leituras";
        }
        catch (NaoEncontradoException e) {
            model.addAttribute("erro", e.getMessage());
        }
        model.addAttribute("leituras", service.listar());
        return "leituras/lista";
    }
}
