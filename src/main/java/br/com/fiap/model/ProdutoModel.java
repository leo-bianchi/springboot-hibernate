package br.com.fiap.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "TB_PRODUTO")
@SequenceGenerator(name = "PRODUTO_SEQ", sequenceName = "PRODUTO_SEQ")
public class ProdutoModel {

	private long id;
	private String nome;
	private String sku;
	private String descricao;
	// Por restrições do banco de dados, o campo float tem que virar BigDecimal
	private BigDecimal preco;
	private String caracteristicas;
	private Date dataLancamento;
	// Para relacionar com categorias
	private CategoriaModel categoriaModel;

	public ProdutoModel() {
	}
	
	public ProdutoModel(long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	// Construtor completo do objeto
	public ProdutoModel(long id, String nome, String sku, String descricao, BigDecimal preco, String caracteristicas,
			Date dataLancamento, CategoriaModel categoriaModel) {
		super();
		this.id = id;
		this.nome = nome;
		this.sku = sku;
		this.descricao = descricao;
		this.preco = preco;
		this.caracteristicas = caracteristicas;
		this.dataLancamento = dataLancamento;
		this.categoriaModel = categoriaModel;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUTO_SEQ")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "NOME")
	@NotNull(message = "Nome obrigatório")
	@Size(min = 2, max = 50, message = "NOME deve ser entre 2 e 50 caracteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "SKU")
	@NotNull(message = "Sku obrigatório")
	@Size(min = 2, max = 40, message = "SKU deve ser entre 2 e 50 caracteres")
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Column(name = "DESCRICAO")
	@NotNull(message = "Descrição obrigatório")
	@Size(min = 10, max = 400, message = "DESCRIÇÃO deve ser entre 10 e 400 caracteres")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "PRECO")
	@DecimalMin(value = "0.01", message = "PRECO deve ser acima de 0.01")
	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
	@Column(name = "CARACTERISTICAS")
	@Size(min = 10, max = 400, message = "CARACTERÍSTICAS deve ser entre 10 e 400 caracteres")
	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	@Column(name = "DATA_LANCAMENTO")
	@NotNull(message = "Data obrigatória no formato dia/mês/ano, exemplo: 10/06/2019")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	// Uma categoria para muitos produtos
	@ManyToOne
	// Coluna que será feito o 'JOIN', deve ser obrigatório
	@JoinColumn(name = "ID_CATEGORIA", nullable = false)
	public CategoriaModel getCategoriaModel() {
		return categoriaModel;
	}

	public void setCategoriaModel(CategoriaModel categoriaModel) {
		this.categoriaModel = categoriaModel;
	}

	@Override
	public String toString() {
		return "ProdutoModel [id=" + id + ", nome=" + nome + ", sku=" + sku + ", descricao=" + descricao + ", preco="
				+ preco + ", caracteristicas=" + caracteristicas + ", dataLancamento=" + dataLancamento
				+ ", categoriaModel=" + categoriaModel + "]";
	}

}
