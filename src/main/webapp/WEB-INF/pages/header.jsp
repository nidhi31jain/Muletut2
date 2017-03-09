<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/png" href="images/logo.png" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="prism/prism.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body>
	<div id="main" class="container-fluid">
		<!-- 		HEADER -->
		<header class="col-xs-12">
			<nav id="navbar-header"
				class="navbar navbar-default navbar-fixed-top">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#nav-menu-div">
							<span>Menu</span>
						</button>
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#mule-menu" id="mule-menu-button">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span><span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="index.html"><img
							src="images/logo.png" class="brand-logo img-responsive"></a>
					</div>
					<div class="collapse navbar-collapse top-navbar" id="nav-menu-div">
						<ul id="nav-menu" class="nav navbar-nav navbar-right">
							<li class="active"><a href="index.html">Home</a></li>
							<li><a href="cloudhub.html">Cloudhub</a></li>
							<!-- 					<li><a href="references.html">References</a></li> -->
							<li><a href="blog.html">Blog</a></li>
							<li><a href="about.html">About me</a></li>
							<li class="form-item"><form:form commandName="search"
									action="search.html" method="post" class="navbar-form"
									role="search">
									<div class="input-group">
										<form:input path="searchString" name="searchString"
											class="form-control pull-right"
											placeholder="Search" required="required" />
										<span class="input-group-btn">
											<button type="submit" class="btn btn-default">
												<span class="glyphicon glyphicon-search"> <span
													class="sr-only">Search</span>
												</span>
											</button>
										</span>
									</div>
								</form:form></li>
						</ul>
					</div>
				</div>
			</nav>
		</header>