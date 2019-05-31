package br.com.fiap.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Todas as models são Entitys
@Entity
// Nome da tabela no banco
@Table(name = "TB_CATEGORIA")
// Nome da sequência que será utilizada
@SequenceGenerator(name = "CATEGORIA_SEQ", sequenceName = "CATEGORIA_SEQ")
public class CategoriaModel {

	private long idCategoria;
	private String nomeCategoria;
	// Para relacionar produtos com categorias
	private List<ProdutoModel> produtos;

	// Construtor Padrão
	public CategoriaModel() {
	}

	// Construtor do objeto
	public CategoriaModel(long idCategoria, String nomeCategoria, List<ProdutoModel> produtos) {
		super();
		this.idCategoria = idCategoria;
		this.nomeCategoria = nomeCategoria;
		this.produtos = produtos;
	}

	// Definimos que será a coluna de Id (chave primária)
	@Id
	// Nome da coluna
	@Column(name = "ID_CATEGORIA")
	// Regra para geração do ID, no caso utiliza a sequência CATEGORIA_SEQ
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CATEGORIA_SEQ")
	public long getIdCategoria() {
		return idCategoria;
	}

	// Nome da coluna
	@Column(name = "NOME_CATEGORIA")
	// Define como obrigatório
	@NotNull(message = "Nome obrigatório")
	// Tamanho mínimo e máximo do campo
	@Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	// Uma categoria pode ter muitos produtos
	@OneToMany(mappedBy = "categoriaModel")
	public List<ProdutoModel> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoModel> produtos) {
		this.produtos = produtos;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
}
