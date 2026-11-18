package br.fiap.luccas563794.controller;

import br.fiap.luccas563794.dto.TrechoDTO;
import br.fiap.luccas563794.service.BairroService;
import br.fiap.luccas563794.service.NaoEncontradoException;
import br.fiap.luccas563794.service.TrechoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/trechos")
@RequiredArgsConstructor
public class TrechoController {
    private final TrechoService service;
    private final BairroService bairroService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("trechos", service.listar());
        return "trechos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("trecho", new TrechoDTO());
        carregarListas(model);
        return "trechos/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("trecho", service.buscarPorId(id));
        carregarListas(model);
        return "trechos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("trecho") TrechoDTO dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            carregarListas(model);
            return "trechos/form";
        }
        service.salvar(dto);
        return "redirect:/trechos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, Model model) {
        try {
            service.excluir(id);
            return "redirect:/trechos";
        }
        catch (NaoEncontradoException e) {
            model.addAttribute("erro", e.getMessage());
        }
        model.addAttribute("trechos", service.listar());
        return "trechos/lista";
    }

    private void carregarListas(Model model) {
        model.addAttribute("bairros", bairroService.listar());
        List<String> tiposCalcada = new ArrayList<>();
        tiposCalcada.add("Regular");
        tiposCalcada.add("Irregular");
        tiposCalcada.add("Escadaria");
        tiposCalcada.add("Sem iluminação");
        model.addAttribute("tiposCalcada", tiposCalcada);
    }
}
