<footer>
	<div id="footer-container" class="col-xs-12 col-sm-10 col-sm-offset-1">
		<nav id="follow-box"
			class="col-xs-offset-3 col-xs-6 col-sm-offset-0 col-sm-3">
			<h2>Follow</h2>
			<a class="follow-buttons linkedin col-xs-12 col-sm-12"
				href="https://www.linkedin.com/Article?mini=true&url=${url}&title=${title}&summary=&source="
				target="_blank"><i class="fa fa-linkedin"></i> LinkedIn</a> <a
				class="follow-buttons twitter col-xs-12 col-sm-12"
				href="https://twitter.com/?url=${url}&text=${title}&via=Muletut"
				target="_blank"><i class="fa fa-twitter"></i> Tweet</a>
			<!-- 				 <a -->
			<!-- 				class="follow-buttons facebook col-xs-12 col-sm-12" -->
			<%-- 				href="https://www.facebook.com/r.php?u=${url}&ext=${title}" --%>
			<!-- 				target="_blank"><i class="fa fa-facebook"></i> Share</a> <a -->
			<!-- 				class="follow-buttons gplus col-xs-12 col-sm-12" -->
			<%-- 				href="https://plus.google.com/?url=${url}" target="_blank"><i --%>
			<!-- 				class="fa fa-google-plus"></i> Share</a> <a -->
			<!-- 				class="follow-buttons slideshare col-xs-12 col-sm-12" href="#" -->
			<!-- 				target="_blank"><i class="fa fa-slideshare"></i> Tweet</a> -->
			<!-- 							<a -->
			<!-- 							class="follow-buttons stumbleupon col-xs-4 col-sm-12" href="#" -->
			<!-- 							target="_blank"><i class="fa fa-stumbleupon"></i> Stumble</a> -->
		</nav>
		<div class="hidden-xs col-sm-3"></div>
		<div id="contact-form" class="col-xs-12 col-sm-6">
			<h2>Drop a Mail</h2>
			<form id="contact-form">
				<div id="contact-details" class="col-xs-12 col-sm-6">
					<input type="text" id = "contact-name" placeholder="Your Name" class="col-xs-12">
					<input type="text" id = "contact-email" placeholder="Your Email" class="col-xs-12">
					<button type = "submit" id = "contact-button" class = "col-xs-12">Drop it</button>
				</div>
				<div id="contact-message" class="col-xs-12 col-sm-6">
					<textarea placeholder="Your Message" class = "col-xs-12"></textarea>
				</div>
			</form>
		</div>
	</div>
	<p class="col-xs-12">Copyright</p>

	<link rel="stylesheet" type="text/css"
		href="bootstrap/bootstrap.min.css">
	<a href="#page" id="scroll-up"><span
		class="glyphicon glyphicon-chevron-up"></span></a>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/jquery-1.11.3.min.js"></script>
	<script src="js/script.js"></script>
	<script src="prism/prism.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="bootstrap/bootstrap.min.js"></script>
	<script>
		window.fbAsyncInit = function() {
			FB.init({
				appId : '293362711077842',
				xfbml : true,
				version : 'v2.8'
			});
			FB.AppEvents.logPageView();
		};

		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) {
				return;
			}
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.8&appId=293362711077842";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
	<script src="https://apis.google.com/js/plusone.js"></script>
</footer>