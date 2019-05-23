package br.com.fiap.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import br.com.fiap.model.CategoriaModel;
import br.com.fiap.model.ProdutoModel;

public class ProdutoRowMapper implements RowMapper<ProdutoModel> {

	@Override
	public ProdutoModel mapRow(ResultSet rs, int rowNum) throws SQLException {

		ProdutoModel produto = new BeanPropertyRowMapper<>(ProdutoModel.class).mapRow(rs, rowNum);
		CategoriaModel categoria = new BeanPropertyRowMapper<>(CategoriaModel.class).mapRow(rs, rowNum);

		produto.setCategoriaModel(categoria);

		return produto;
	}

}