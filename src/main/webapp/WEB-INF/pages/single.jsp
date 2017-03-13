<%@ include file="header.jsp"%>
<c:set var="title" value="${fn:replace(postName,'-', ' ')}"></c:set>
<div id="main-content-single" class="col-xs-12 row">
	<nav class="col-md-3 hidden-xs hidden-sm">
		<ul id="blog-posts" class="col-sm-12">
			<c:forEach items="${posts}" var="post" varStatus="status">
				<a href="${post}.htm"><li id="posts" class="col-md-12">${fn:replace(post,"-", " ")}</li></a>
			</c:forEach>
		</ul>
	</nav>
	<div class="col-md-9 col-xs-12" id="post-container">
		<article id="post-area" class="row">
			<div id="heading" class="col-xs-12">
				<h2 class="title">${title}</h2>
			</div>
			<article id="post-content" class="col-xs-12"></article>
		</article>
		<ul class="pager col-xs-12">
			<li class="previous"><a href="#">Previous</a></li>
			<li class="next"><a href="#">Next</a></li>
		</ul>
		<nav id="share-icons">
			<a class="share-buttons share linkedin"
				href="https://www.linkedin.com/shareArticle?mini=true&url=${url}&title=${title}&summary=&source="
				target="_blank"><i class="fa fa-linkedin"></i> LinkedIn</a> <a
				class="share-buttons share twitter"
				href="https://twitter.com/share?url=${url}&text=${title}&via=Muletut"
				target="_blank"><i class="fa fa-twitter"></i> Tweet</a> <a
				class="share-buttons share facebook"
				href="https://www.facebook.com/sharer.php?u=${url}&ext=${title}"
				target="_blank"><i class="fa fa-facebook"></i> Share</a> <a
				class="share-buttons share gplus"
				href="https://plus.google.com/share?url=${url}" target="_blank"><i
				class="fa fa-google-plus"></i> Share</a> <a
				class="share-buttons share slideshare" href="#" target="_blank"><i
				class="fa fa-slideshare"></i> Tweet</a> <a
				class="share-buttons share stumbleupon" href="#" target="_blank"><i
				class="fa fa-stumbleupon"></i> Stumble</a>
		</nav>
</div>
<%@ include file="footer.jsp"%>
</div>
</body>
</html>