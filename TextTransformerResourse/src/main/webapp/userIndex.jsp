<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
header {
	background-color: lightblue;
	padding-top: 50px;
	padding-left: 25px;
	padding: 25px;
	text-align: left;
	height: 50px;
	padding-left: 25px;
}

.styled-button {
	/* Стилизация для внешнего вида */
	display: inline-block; /* Чтобы можно было задавать отступы */
	padding: 10px 20px;
	font-family: PT Sans;
	font-weight: normal;
	z-index: 1;
	background-color: orange; /* Цвет фона */
	color: white; /* Цвет текста */
	border: none; /* Убираем стандартную рамку ссылки */
	text-align: center;
	text-decoration: none; /* Убираем подчеркивание */
	font-size: 16px;
	cursor: pointer; /* Курсор в виде руки */
	border-radius: 5px; /* Скругленные углы */
}

.styled-button:hover {
	font-family: PT Sans;
	font-weight: normal;
	z-index: 1;
	background-color: lightgrey; /* Цвет при наведении */
}

.dropdown {
	font-family: PT Sans;
	font-weight: normal;
	position: relative; /* Для позиционирования выпадающего контента */
	display: inline-block;
}

.dropdown-content {
	font-family: PT Sans;
	font-weight: normal;
	display: none; /* Скрыть изначально */
	position: absolute; /* Позиционировать относительно .dropdown */
	background-color: #f9f9f9;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	font-family: PT Sans;
	font-weight: normal;
	color: white;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown:hover .dropdown-content {
	display: block; /* Показать при наведении */
	background-color: transparent;
	font-family: PT Sans;
	font-weight: normal;
	color: white;
	border: 1px;
}

.styled-button2 {
	/* Стилизация для внешнего вида */
	display: inline-block; /* Чтобы можно было задавать отступы */
	padding: 10px 20px;
	font-family: PT Sans;
	font-weight: normal;
	z-index: 1;
	background-color: orange; /* Цвет фона */
	color: white; /* Цвет текста */
	border: none; /* Убираем стандартную рамку ссылки */
	text-align: center;
	text-decoration: none; /* Убираем подчеркивание */
	font-size: 16px;
	cursor: pointer; /* Курсор в виде руки */
	border-radius: 5px; /* Скругленные углы */
}

.styled-button2:hover {
	font-family: PT Sans;
	font-weight: normal;
	z-index: 1;
	background-color: lightgrey; /* Цвет при наведении */
}

.text-box {
	font-family: PT Sans;
	font-weight: normal;
	font-size: 18px;
	background-color: lightblue;
	justify-content: center;
	align-items: center;
	border: 0px solid darkgrey;
	margin-top: 4px;
	margin-left: 1px;
	margin-down: 1px;
	text-align: center;
	box-sizing: border-box;
	padding: 60px
}

.container {
	font-family: PT Sans;
	font-weight: normal;
	display: flex;
	position: relative;
	justify-content: space-between;
	box-sizing: border-box;
}

.text-box2 {
	font-family: PT Sans;
	font-weight: normal;
	width: 510px;
	padding: 60px;
	border: 0px solid darkgrey;
	background-color: #f0f0f0;
	margin-top: 6px;
	margin-left: 1px;
	margin-down: 0px;
	text-align: justify;
	word-wrap: break-word;
	overflow-x: hidden;
}

.button-link {
	color: darkblue;
	cursor: pointer;
	text-decoration: none;
	font-weight: bold;
}

.button-link2 {
	color: darkblue;
	cursor: pointer;
	text-decoration: none;
	font-weight: bold;
}

.button-link:hover {
	color: orange;
	z-index: 1;
	background-color: transparent; /* Цвет при наведении */
	text-decoration: underline;
}

.email-button {
	display: inline-block;
	/* padding: 0px 8px;*/
	/* background-color: lightgrey;*/ * Зеленый фон */ text-color :
	darkblue;
	color: orange; /* Белый текст */
	text-decoration: none; /* Убираем подчеркивание */
	border-radius: 5px; /* Скругленные углы */
	border: none; /* Убираем рамку */
	cursor: pointer; /* Курсор-рука при наведении */
	font-size: 18px;
	font-weight: bold;
}

.email-button:hover {
	background-color: orange; /* Темнее при наведении */
	padding: 0px 8px;
	font-size: 18px;
	color: white; /* Белый текст */
	text-decoration: none;
}

.wrapper {
	font-family: PT Sans;
	font-weight: normal;
	margin-top: 0px;
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
	border: 0px solid #bebebe;
	text-align: center;
	height: 500px;
	*/
}

.footer {
	font-family: PT Sans;
	font-weight: normal;
	margin-top: 12px;
	/* Подвал прижимается к низу, занимая оставшееся пространство */
	background-color: #f1f1f1;
	padding: 20px;
	text-align: center;
	background-color: lightblue;
}

.text-box3 {
	box-sizing: border-box;
	font-family: PT Sans;
	font-weight: normal;
	font-size: 18px;
	background-color: #f0f0f0;
	justify-content: center;
	align-items: center;
	border: 0px solid darkgrey;
	margin-top: 7px;
	margin-left: 1px;
	margin-down: 1px;
	text-align: justify;
	padding: 30px;
	overflow-x: hidden;
}
</style>

<title>User's page</title>
</head>
<body>
	<center>

		<header>
			<div class="c">
				<a class="styled-button2" href="http://localhost:8080" id="index">MAIN</a>
				<div class="dropdown">
					<button class="styled-button2 id="menu">SERVICES</button>
					<div class="dropdown-content">
						<a class="styled-button"
							href="http://localhost:8080/PunctuationMarksRemover.jsp"
							id="PunctuationMarksRemover">PunctuationMarksRemover</a> <a
							class="styled-button"
							href="http://localhost:8080/UpperCaseTransformer.jsp"
							id="UpperCaseTransformer">ToUpperCaseTransformer</a> <a
							class="styled-button"
							href="http://localhost:8080/LowerCaseTransformer.jsp"
							id="LowerCaseTransformer">ToLowerCaseTransformer</a> <a
							class="styled-button"
							href="http://localhost:8080/LineBreaksRemover.jsp"
							id="LineBreaksRemover">LineBreaksRemover</a><a
							class="styled-button"
							href="http://localhost:8080/DownloadFileSample.jsp"
							id="DownloadFileSample">DownloadFileSample</a>
					</div>
				</div>
				<a class="styled-button" href="http://localhost:8080/About.jsp"
					id="About">ABOUT US</a> <a
					href="mailto:oi243012@gmail.com?subject=Letter topic&body=Text of the letter"
					class="styled-button2" id="writeUs">WRITE US</></a>
				
				<button class="styled-button"
					style="float: right; margin-top: 0.5px" ; button
					onclick="window.location.href = 'http://localhost:8080/ru/index.jsp';"
					id="ru/index">RU</button>
					
					<div class="dropdown"style="float: right; margin-right: 3px;margin-top: 0.5px">									
					<button class="styled-button" id="userMenu"><%=request.getSession().getAttribute("user")%></button>
					<div class="dropdown-content" style="margin-top: 1px">
											<a class="styled-button"
							href="http://localhost:8080/UserProfile.jsp"
							id="profile">Profile</a>
							<a class="styled-button"
							href="http://localhost:8080/UserDownloads.jsp"
							id="profile">Downloads</a>
							<a class="styled-button"
							href="http://localhost:8080/index.jsp"
							id="profile">Logout</a>
					
					</div>									
					</div>									
				</a>
		</header>
	</center>

	<div class="text-box">

		<h3>We constantly improve and upgrade our resource to make it
			more efficient and usable for you. Please, choose needed service and
			enjoy its usability. Also, we'll be happy to hear some feedbacks from
			you.</h3>
	</div>

	<div class="container">
		<div class="text-box2" style="font-size: 18px;">
			The project <a class="button-link2" href="http://localhost:8080"
				id="index">TextTransformer </a> is first project and we kindly ask
			you to take any meet mistakes and bugs with understanding, as the
			team is only looking for some expirience and provide this resource
			only basing on personal needs and ready to solve any problems by
			request.
		</div>

		<div class="text-box2"
			style="margin-left: 7px; font-size: 18px; position: relative;">
			The resource offers several different services connected with text
			and text files transformations and edits and if you have any
			suggestions or requests about new services, which might be presented
			here, kindly <a
				href="mailto:oi243012@gmail.com?subject=Letter topic&body=Text of the letter"
				class="email-button" id="writeUs"> write us on email</a>

		</div>

	</div>
	<div class="wrapper">
		<div class="text-box3" style="width: 67%; height: 500px; order: 2;">
			So here is the text transformation services our resource is ready to
			offer you:
			<p>
				<a class="button-link"
					href="http://localhost:8080/UpperCaseTransformer.jsp"
					id="UpperCaseTransformer">ToUpperCaseTransformer</a> - pretty
				simple service, which allows you to transform all your text into
				text in upper case
			<p>
				<a class="button-link" href="address/LowerCaseTransformer.jsp"
					id="LowerCaseTransformer">ToLowerCaseTextTransformer </a> - same
				simple service, which help you to transform all input text into text
				in lower case
			<p>
				<a class="button-link" href="address/PunctuationMarksRemover.jsp"
					id="PunctuationMarksRemover">PunctuationMarksRemover </a> - this
				simple function allows you to remove any punctuation marks from your
				text, keeping the text untouched
			<p>
				<a class="button-link" href="address/LineBreaksRemover.jsp"
					id="LineBreaksRemover">LineBreaksRemover </a> - this function allow
				you to copy and paste your text from any previously formatted image
				and get it without any unneeded line breaks
			<p>
				<a class="button-link"
					href="address/CapitalFirstLetterTransformer.jsp"
					id="LineBreaksRemover">CapitalFirstLetterTransformer </a> - you can
				place your text and get it with first letters of each sentence
				converted into capital
		</div>
		<div class="text-box3" style="width: 32.5%; height: 205px; order: 1;">
			On this resourse you can transform your text, copy it into clipboard
			and paste transformed result in any text editor or service you want.
			Also, you can download the result of required transformation into
			.txt file</div>
		<div class="text-box3"
			style="width: 32.5%; height: 290px; order: 3; transform: translateY(-297px);">
			Please, note, that resource do not provide any work with tables, so
			make sure the file you want to upload do not contains any table.
			Currently we work with .pages, .doc, .docx, .vtt, .srt and .txt files
			and list expands constantly.</div>
	</div>
	<div class="footer">All rights reserved @byOlegov 2026</div>
</body>

<script>

const urlSender = () => {
	 let buttonId = event.target.id;
	 let pageTitle = document.title;
  	 let home = 'http://localhost:8080/';
   	 let clickData = '{action:Transition from page http://localhost:8080/index.jsp to page '+home+buttonId+'.jsp'+'}';
   	 fetch('http://localhost:8080/goToPage', {
   	
       method: 'POST',
       headers: {
           'Content-Type': 'application/json',
       },
       body: JSON.stringify(clickData)
   })
   .then(response => {
       if (!response.ok) {
           throw new Error('Ошибка сети');
       }
       return response.json();
   })
   .then(data => {
       console.log('Успех:', data);
   })
   .catch(error => {
       console.error('Ошибка:', error);
   });
   
 };
 
 const buttons = document.querySelectorAll('.styled-button');

 buttons.forEach(button => {
 button.addEventListener('click', urlSender);
 });
 
 const urlClickerSender = () => {
	 let buttonId2 = event.target.id;
	 let pageTitle2 = document.title;

   	 let clickData2 = '{action:Button on page http://localhost:8080/'+pageTitle2+'.jsp clicked: '+buttonId2+'}';
   	 fetch('http://localhost:8080/clicked', {
   	
       method: 'POST',
       headers: {
           'Content-Type': 'application/json',
       },
       body: JSON.stringify(clickData2)
   })
   .then(response => {
       if (!response.ok) {
           throw new Error('Ошибка сети');
       }
       return response.json();
   })
   .then(data => {
       console.log('Успех:', data);
   })
   .catch(error => {
       console.error('Ошибка:', error);
   });
   
 };


 
 const buttons2 = document.querySelectorAll('.styled-button2');

 buttons2.forEach(button => {
 button.addEventListener('click', urlClickerSender);
 });
 
 const buttons3 = document.querySelectorAll('.button-link');

 buttons3.forEach(button => {
 button.addEventListener('click', urlSender);
 });
 
 const buttons4 = document.querySelectorAll('.email-button');

 buttons4.forEach(button => {
 button.addEventListener('click', urlClickerSender);
 });
 
 const buttons5 = document.querySelectorAll('.button-link2');

 buttons5.forEach(button => {
 button.addEventListener('click', urlClickerSender);
 });
</script>


</html>
</html>
