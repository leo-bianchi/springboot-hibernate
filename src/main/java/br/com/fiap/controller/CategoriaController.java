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

// Devemos usar a notação para indicar que a classe é Controller
@Controller
// Definindo que tudo estará dentro do caminho /categoria
@RequestMapping("/categoria")
public class CategoriaController {
	
	// Definimos 'categoria/' como uma constante para não ter que repetir inúmeras vezes no código
	private static final String CATEGORIA_PATH = "categoria/";

	// Instanciamos a CategoriaRepository para acessarmos os métodos do Hibernate
	@Autowired
	public CategoriaRepository categoriaRepository;
	
	// Listagem
	/* Passamos uma Model como parâmetro e adicionamos os valores recebidos na variável
	"categorias" (utilizaremos esse nome na .jsp. 
	O retorno será o nome do arquivo .jsp para onde desejamos que o usuário seja encaminhado
	Utilizamos o método GET para buscas -> GetMapping */
	@GetMapping("/relatorio") // caminho na url (/categoria/relatorio)
	public String getProductsByCategories(Model model) {
		// Adicionando os dados do banco a variável "categorias"
		model.addAttribute("categorias", categoriaRepository.findAll());
		// Enviar para o arquivo categoria/categorias-relatorio.jsp
		return CATEGORIA_PATH + "categorias-relatorio";
	}
	
	// Busca
	// RequestParam porque receberemos um parâmetro, no caso isNew
	@GetMapping()
	public String get(@RequestParam(required=false) boolean isNew, Model model) {	
		// Se isNew for true, irá criar uma nova categoriaModel
		if(isNew) {
			model.addAttribute("categoriaModel", new CategoriaModel());	
		} else {
		// Se for falso fara a busca de categorias
			model.addAttribute("categorias", categoriaRepository.findAll());	
		}
		// If ternário
		// Se isNew for true, leva para categoria-novo, senão, para categorias
		return CATEGORIA_PATH + (isNew ? "categoria-novo" : "categorias");
	}
	
	// Mapeia um id para url
	@GetMapping(value = "/{id}")
	public String getById(@PathVariable("id") long id, @RequestParam(required=false) boolean isEdit, Model model) {
		// No Hibernate quando utilizamos findById(id) deve ter um .get() no final para funcionar.
		model.addAttribute("categoriaModel", categoriaRepository.findById(id).get());
		
		// Se isEdit for true, encaminha para categoria-editar, senão para categoria-detalhe
		return CATEGORIA_PATH + (isEdit ? "categoria-editar": "categoria-detalhe");
	}
	
	// Cadastro
	// Para cadastro utilizamos método POST
	@PostMapping()
	public String save(@Valid CategoriaModel categoriaModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		// Se não tiver erros
		if (!bindingResult.hasErrors()) {
			// Envia a mensagem de sucesso e redireciona para a página da categoria criada
			redirectAttributes.addFlashAttribute("mensagemCategoria", "Categoria cadastrada com sucesso");
			categoriaRepository.save(categoriaModel);
			return "redirect:/categoria";
		} else {
			return CATEGORIA_PATH + "categoria-novo";
		}
	}
	
	// Edicao
	// Edição utiliza método PUT
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
		// Recebe o ID da categoria a ser deletada
		categoriaRepository.deleteById(id);
		// Envia a mensagem
		redirectAttributes.addFlashAttribute("mensagemCategoria", "Categoria excluída com sucesso");
		return "redirect:/categoria";
	}
	
	
}
