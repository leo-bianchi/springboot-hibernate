<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>

<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Small Business - Start Bootstrap Template</title>
  
    <c:set value="${pageContext.request.contextPath}" var="contextPath"/>
      
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/small-business.css" rel="stylesheet">

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                    <img src="http://placehold.it/150x50&text=Logo" alt="">
                </a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${contextPath}/categoria">Categorias</a>
                    </li>
                    <li>
                        <a href="${contextPath}/produto">Produtos</a>
                    </li>
                    <li>
                        <a href="#">Contato</a>
                    </li>
					<li>
                        <a href="#">Tipos de Contato</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">

        <!-- Heading Row -->
        <div class="row">
						
            <div class="col-md-12">
				<h1>Produtos</h1>
				
				<p class="toolbar">
				
					<a class="create btn btn-default" href="${contextPath}/produto?isNew=true">Novo Produto</a>
	
					<span class="alert"></span>
				</p>
				
				<c:if test="${not empty mensagemProduto}">
					<div class="alert alert-warning" role="alert">
						<h4>${mensagemProduto}</h4>
					</div>
				</c:if>
				
				<table class="table table-striped" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<th data-field="name">Nome</th>
							<th data-field="categoria">Categoria</th>				
							<th data-field="preco">Preço</th>
							<th data-field="lancamento">Lançamento</th>
							<th class="actions" width="220">Ações</th>
						</tr>
					</thead>
					<tbody>
					
					<c:forEach items="${produtos}" var="produto">
						<tr>
							<td>${produto.nome}</td>
							<td>${produto.categoriaModel.nomeCategoria}</td>
							<td>${produto.preco}</td>
							<td><fmt:formatDate pattern = "dd/MM/yyyy" value = "${produto.dataLancamento}" /></td>
							
							<td class="actions">
							
								<form:form method="delete" action="${contextPath}/produto/${produto.id}">
								
									<a class="btn btn-success btn-xs" href="${contextPath}/produto/${produto.id}?isEdit=false">Detalhes</a>
									<a class="btn btn-warning btn-xs" href="${contextPath}/produto/${produto.id}?isEdit=true">Editar</a>
									<input type="submit" value="Excluir" class="btn btn-danger btn-xs">
									
								</form:form>
								
							</td>
							
						</tr>
					</c:forEach>
						

                    </tbody>
				</table>
				
				
				
            </div>
            <!-- /.col-md-4 -->
        </div>
        <!-- /.row -->

		<hr>
			
    </div>
	
	<!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright &copy; Your Website 2014</p>
            </div>
        </div>
    </footer>
	
    <!-- jQuery -->
    <script src="/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/js/bootstrap.min.js"></script>

</body>

</html>
