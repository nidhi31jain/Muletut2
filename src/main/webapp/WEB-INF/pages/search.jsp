<%@ include file="header.jsp"%>
<div id="main-content-search" class="col-xs-12">
	<div class="col-md-10 col-sm-12 col-md-offset-1" id="results">
	<h3>Search results for "${searchString}"</h3>
		<nav id="result-posts-area">
			<ul id="result-posts">
				<c:forEach items="${titles}" var="title" varStatus="status">
					<a href="index.html#${title}"><li id="posts" class="col-xs-12 col-sm-4">${fn:replace(title,"-", " ")}</li></a>
				</c:forEach>
			</ul>
		</nav>
	</div>
</div>
<%@ include file="footer.jsp"%>
</div>
</body>
</html>
