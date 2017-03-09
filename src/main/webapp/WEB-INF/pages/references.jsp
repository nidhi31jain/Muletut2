<%@ include file="header.jsp"%>
<div id="main-content" class="container-fluid row">
	<aside class="col-sm-3 container sidebar">
		<div>
			<ul id="mule-menu">
				<c:forEach items="${menuItems}" var="item" varStatus="status">
					<li ${status.first ? 'class="active first"' : '' }><a
						href="#${item}">Mule - ${item}</a></li>
				</c:forEach>
			</ul>
		</div>
	</aside>
	<div class="col-sm-9" id="post">
		<div id="post-area">
			<div class="loader">
				<div>
					<img src="images/loader.png" class="img-responsive">
				</div>
			</div>
			<h2 class="title"></h2>
			<hr class="dotted-line">
			<div id="post-content"></div>
		</div>
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
