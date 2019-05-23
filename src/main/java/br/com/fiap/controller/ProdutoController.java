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

import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.CategoriaRepository;
import br.com.fiap.repository.ProdutoRepository;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	private static final String PRODUTO_PATH = "produto/";
	
	@Autowired
	public ProdutoRepository produtoRepository;
	
	@Autowired
	public CategoriaRepository categoriaRepository;
	
	// Busca
	@GetMapping()
	public String get(@RequestParam(required=false) boolean isNew, Model model) {	
		
		if(isNew) {
			model.addAttribute("produtoModel", new ProdutoModel());
			model.addAttribute("categorias", categoriaRepository.findAll());
		} else {
			model.addAttribute("produtos", produtoRepository.findAll());	
		}
		
		return PRODUTO_PATH + (isNew ? "produto-novo" : "produtos");
	}
	
	@GetMapping(value = "/{id}")
	public String getById(@PathVariable("id") long id, @RequestParam(required=false) boolean isEdit, Model model) {

		model.addAttribute("produtoModel", produtoRepository.findById(id).get());
		
		if(isEdit) {
			model.addAttribute("categorias", categoriaRepository.findAll());
		}
		
		return PRODUTO_PATH + (isEdit ? "produto-editar": "produto-detalhe");
	}
	
	// Cadastro
	@PostMapping()
	public String newProduct(@Valid ProdutoModel produtoModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		
		if (!bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("mensagemProduto", "Produto cadastrado com sucesso");
			produtoRepository.save(produtoModel);
			return "redirect:/produto";
		} else {
			model.addAttribute("categorias", categoriaRepository.findAll());
			return PRODUTO_PATH + "produto-novo";
		}
	}
	
	// Edicao
	@PutMapping()
	public String editProduct(@Valid ProdutoModel produtoModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		
		if (!bindingResult.hasErrors()) {
			produtoRepository.save(produtoModel);
		
			redirectAttributes.addFlashAttribute("mensagemProduto", "Produto alterado com sucesso");
			return "redirect:/produto";
		} else {
			model.addAttribute("categorias", categoriaRepository.findAll());
			return PRODUTO_PATH + "produto-editar";
		}
	}
	
	
	// Deleção	
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		
		produtoRepository.deleteById(id);
		redirectAttributes.addFlashAttribute("mensagemProduto", "Produto excluído com sucesso");
		return "redirect:/produto";
	}
	
}
