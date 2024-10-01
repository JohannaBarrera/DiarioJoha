package com.company.spring_boot_crud_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entradas")
public class EntradaWebController {
    private static final String REDIRECT_ENTRADAS = "redirect:/entradas";
    private final EntradaRepository entradaRepository;
    private final EtiquetaRepository etiquetaRepository; 

    public EntradaWebController(EntradaRepository entradaRepository, EtiquetaRepository etiquetaRepository) {
        this.entradaRepository = entradaRepository;
        this.etiquetaRepository = etiquetaRepository;
    }

    @GetMapping
    public String listarEntradas(Model model) {
        model.addAttribute("entradas", entradaRepository.findAll());
        return "entradas/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("entrada", new Entrada());
        model.addAttribute("etiquetas", etiquetaRepository.findAll()); 
        return "entradas/formulario";
    }

    @PostMapping("/nuevo")
    public String crearEntrada(@ModelAttribute Entrada entrada) {
        entradaRepository.save(entrada);
        return REDIRECT_ENTRADAS;
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Entrada entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de entrada inválido: " + id));
        model.addAttribute("entrada", entrada);
        model.addAttribute("etiquetas", etiquetaRepository.findAll());
        return "entradas/formulario";
    }

    @PostMapping("/editar/{id}")
    public String actualizarEntrada(@PathVariable Long id, @ModelAttribute Entrada entrada) {
        entrada.setId(id);
        entradaRepository.save(entrada);
        return REDIRECT_ENTRADAS;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEntrada(@PathVariable Long id) {
        entradaRepository.deleteById(id);
        return REDIRECT_ENTRADAS;
    }
}