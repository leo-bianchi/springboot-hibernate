package br.com.fiap.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.model.CategoriaModel;
import br.com.fiap.repository.CategoriaRepository;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	private static final String CATEGORIA_PATH = "categoria/";

	@Autowired
	public CategoriaRepository categoriaRepository;
	
	@GetMapping("/relatorio")
	public String getProductsByCategories(Model model) {
		model.addAttribute("categorias", categoriaRepository.findAll());
		return CATEGORIA_PATH + "categorias-relatorio";
	}
	
	// Busca
	@GetMapping()
	public String get(@RequestParam(required=false) boolean isNew, Model model) {	
		
		if(isNew) {
			model.addAttribute("categoriaModel", new CategoriaModel());	
		} else {
			model.addAttribute("categorias", categoriaRepository.findAll());	
		}
		
		return CATEGORIA_PATH + (isNew ? "categoria-novo" : "categorias");
	}
	
	@GetMapping(value = "/{id}")
	public String getById(@PathVariable("id") long id, @RequestParam(required=false) boolean isEdit, Model model) {

		model.addAttribute("categoriaModel", categoriaRepository.findById(id));
		
		return CATEGORIA_PATH + (isEdit ? "categoria-editar": "categoria-detalhe");
	}
	
	// Cadastro
	@PostMapping()
	public String save(@Valid CategoriaModel categoriaModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (!bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("mensagemCategoria", "Categoria cadastrada com sucesso");
			categoriaRepository.save(categoriaModel);
			return "redirect:/categoria";
		} else {
			return CATEGORIA_PATH + "categoria-novo";
		}
	}
	
	// Edicao
	@PutMapping()
	public String edit(@Valid CategoriaModel categoriaModel, 
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (!bindingResult.hasErrors()) {
			categoriaRepository.save(categoriaModel);
		
			redirectAttributes.addFlashAttribute("mensagemCategoria", "Categoria alterada com sucesso");
			return "redirect:/categoria";
		} else {
			return CATEGORIA_PATH + "categoria-editar";
		}
	}
	
	
	// Deleção	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		
		categoriaRepository.deleteById(id);
		redirectAttributes.addFlashAttribute("mensagemCategoria", "Categoria excluída com sucesso");
		return "redirect:/categoria";
	}
	
	
}
