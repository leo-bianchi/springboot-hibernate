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

@Entity
@Table(name = "TB_CATEGORIA")
@SequenceGenerator(name = "CATEGORIA_SEQ", sequenceName = "CATEGORIA_SEQ")
public class CategoriaModel {

	private long idCategoria;
	private String nomeCategoria;
	private List<ProdutoModel> produtos;

	public CategoriaModel() {
	}

	public CategoriaModel(long idCategoria, String nomeCategoria, List<ProdutoModel> produtos) {
		super();
		this.idCategoria = idCategoria;
		this.nomeCategoria = nomeCategoria;
		this.produtos = produtos;
	}

	@Id
	@Column(name = "ID_CATEGORIA")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CATEGORIA_SEQ")
	public long getIdCategoria() {
		return idCategoria;
	}

	@Column(name = "NOME_CATEGORIA")
	@NotNull(message = "Nome obrigatório")
	@Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

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
