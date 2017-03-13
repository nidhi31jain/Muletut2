<%@ include file="header.jsp"%>
<div id="main-content" class="container-fluid row">
	<nav class="col-sm-3 container sidebar">
			<ul id="mule-menu" class = "navbar-collapse collapse">
				<c:forEach items="${menuItems}" var="item" varStatus="status">
					<li ${status.first ? 'class="active first"' : '' }><a
						href="#${item}">Mule - ${fn:replace(item,"-", " ")}</a></li>
				</c:forEach>
			</ul>
	</nav>
	<div class="col-sm-9" id="post-container">
<!-- 		<div class="col-sm-9 loader"> -->
<!-- 			<div class="col-sm-offset-3 col-sm-9 "> -->
<!-- 				<p id="loading-msg"> -->
<!-- 					Loading<br>Please Wait -->
<!-- 				</p> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<article id="post-area" class="col-xs-12">
			<h2 class="title"></h2>
			<hr class="dotted-line">
			<article id="post-content"></article>
		</article>
		<hr>
		<ul class="pager">
			<li class="previous"><a href="#">Previous</a></li>
			<li class="next"><a href="#">Next</a></li>
		</ul>
	</div>
</div>
<%@ include file="footer.jsp"%>
</div>
</body>
</html>