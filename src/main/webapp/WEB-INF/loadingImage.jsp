<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script>
	window.onpageshow = function (event){     //뒤로가기로 페이지 접근했는지 확인
	    if(event.persisted || (window.performance && window.performance.navigation.type == 2) || (window.performance.getEntriesByType("navigation")[0].type == "back_forward")){

			document.getElementById('loading').style.display = 'none';
	        
	    }
	}
	</script>
	<div id="loading">
		<div>
			<svg version="1.1" id="loader-1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
			   width="60px" height="60px" viewBox="0 0 50 50" xml:space="preserve">
			    <path fill="#000" d="M43.935,25.145c0-10.318-8.364-18.683-18.683-18.683c-10.318,0-18.683,8.365-18.683,18.683h4.068c0-8.071,6.543-14.615,14.615-14.615c8.072,0,14.615,6.543,14.615,14.615H43.935z">
				    <animateTransform attributeType="xml"
				      attributeName="transform"
				      type="rotate"
				      from="0 25 25"
				      to="360 25 25"
				      dur="0.6s"
				      repeatCount="indefinite"/>
		        </path>
		    </svg>
		</div>
	</div>
</body>
</html>