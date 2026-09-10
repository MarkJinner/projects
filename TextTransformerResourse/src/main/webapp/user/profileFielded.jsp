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

body {
	background-color: lightblue;
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
	border-bottom: 5px solid #ccc;
	border-top: 5px solid #ccc;
	border-left: 5px solid #ccc;
	border-right: 5px solid #ccc;
	background-color: lightblue;
	justify-content: center;
	align-items: center;
	margin-top: 4px;
	margin-left: 35px;
	margin-right: 35px;
	margin-down: 1px;
	text-align: center;
	box-sizing: border-box;
	padding: 60px padding-bottom: 10px;
	padding-top: 20px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
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

.btn-user {
	display: flex; /* Чтобы можно было задавать отступы */
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
	justify-content: center;
}

.btn-user img {
	/* Изображение будет слева */
	margin-right: 10px; /* Небольшой отступ от текста */
	justify-content: center;
}

.btn-user span {
	/* Текст будет справа */
	
}

.btn-user:hover {
	font-family: PT Sans;
	font-weight: normal;
	z-index: 1;
	background-color: lightgrey; /* Цвет при наведении */
}

.form-container {
	margin-top: 70px;
	justify-content: center;
	align-items: left;
	display: flex;
	flex-direction: column; /* Поля под полями */
	gap: 30px; /* Расстояние между строками */
}

.form-row {
	width: 250px;
	/* 	text-overflow: ellipsis; */
	display: flex;
	align-items: center; /* Вертикальное выравнивание внутри строки */
}

.form-row label {
	position: relative;
	width: 80px; /* Фиксированная ширина меток */
	text-align: right; /* Выравнивание текста метки */
	margin-right: 20px; /* Отступ от метки до поля */
}

.form-row input {
	/* position: absolute; */
	margin-right: 20px; /* Отступ от поля до кнопки */
}

.form-errorField {
	position: absolute;
	align-items: center;
	text-align: center;
	top: 40px;
}

.placeholder {
	width: 350px;
	height: 30px;
	/* border-radius: 3px; */
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1); 
  	justify-content: flex-end;
  	background-color: white; 
}.placeholder:disabled {
	width: 350px;
	height: 30px;
	/* border-radius: 3px; */
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1); 
	background-color: lightgrey; 
   color: #888; /* Цвет по умолчанию */
   opacity: 1; /* Для Firefox */
}

</style>

<title>User's page</title>
</head>
<body>
	<center>

		<header>
			<div class="c">
				<a class="styled-button2"
					href="http://localhost:8080/user/index.jsp" id="index">MAIN</a>
				<div class="dropdown">
					<button class="styled-button2 id="menu">SERVICES</button>
					<div class="dropdown-content">
						<a class="styled-button"
							href="http://localhost:8080/PunctuationMarksRemover.jsp"
							id="PunctuationMarksRemover">PunctuationMarksRemover</a> <a
							class="styled-button"
							href="http://localhost:8080/user/UpperCaseTransformer.jsp"
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

				<div class="dropdown"
					style="float: right; margin-right: 3px; margin-top: 0.5px;">
					<button class="btn-user" style="background-color: none;"
						id="userMenu">
						<img src="/images/free-icon-avatar-6386976.png"
							style="max-width: 16.5px; height: auto;"><%=request.getSession().getAttribute("user")%></button>
					<div class="dropdown-content"
						style="margin-top: 1px; tex-align: left;">
						<a class="styled-button" style="tex-align: left"
							href="http://localhost:8080/user/profile.jsp" id="profile">Profile</a>
						<a class="styled-button"
							href="http://localhost:8080/user/downloads.jsp" id="downloads">Downloads</a>
						<a class="styled-button" href="http://localhost:8080/index.jsp"
							id="logoutButton">Logout</a>

					</div>
				</div>
				</a>
		</header>
	</center>
	<div class="text-box"
		style="height: 80px; text-align: left; background-color: light-orange;">
		<div class="form-row" style="padding-left: 20px; margin-top: -20px;">
			<h3>User's profile</h3>
		</div>
	</div>
	<div class="text-box" style="height: 370px;">

		<%-- 		<h3>USER'S PROFILE FOR <%= request.getSession().getAttribute("user")%> --%>
		<%@ page import="com.gmail.database.*"%>
		<%@ page import="java.util.Date"%>
		<%String userName = request.getSession().getAttribute("user").toString();%>
		<%User user = new UsersDatabase().findByLogin(userName).get();%>
		<%-- 			<p>User's email: <%=user.getEmail()%></p>
			<p>User's login: <%=user.getLogin()%></p>
			<p>User's password: <%=user.getPassword()%></p> --%>

		<p>
		<center>
			<div class="form-container" style="margin-right: 25px;">
				<center>
					<div class="form-row" style="margin-right: 60px">
						<label for="password">Password:</label> <input type="Text"
							style="width: 350px;" id="password"
							value="<%=user.getPassword()%>" disabled class="placeholder">
						<input type="button" button id="actionButton1" value="Edit" button
							onclick="processClick('button 1 clicked','password','actionButton1','errorField1')"
							class="styled-button">
						<div class="form-errorField" id="errorField1"
							style="color: red; font-size: 12px; display: none; margin-left: 95px; margin-top: 295px"></div>

					</div>
					<br>


					<div class="form-row">
						<label for="login">Login:</label> <input type="Text" id="login"
							value="<%=user.getLogin()%>" disabled class="placeholder">
						<input type="button" button id="actionButton2" value="Edit" button
							onclick="processClick('button 2 clicked','login','actionButton2','errorField2')"
							class="styled-button">

						<div class="form-errorField" id="errorField2"
							style="color: red; font-size: 12px; display: none; margin-left: 65px; margin-top: 355px"></div>

					</div>
					<br>
					<div class="form-row">
						<label for="email">Email:</label> <input type="Text" id="email"
							value="<%=user.getEmail()%>" disabled class="placeholder">
						<input type="button" button id="actionButton3" value="Edit" button
							onclick="processClick('button 3 clicked','email','actionButton3','errorField3')"
							class="styled-button">
						<div class="form-errorField" id="errorField3"
							style="color: red; font-size: 12px; display: none; margin-left: 65px; margin-top: 420px"></div>
					</div>
				</center>
			</div>
		</center>

		</h3>
	</div>



	<center>
		<div class="footer">All rights reserved @byOlegov 2026</div>
	</center>
</body>

<script>

const urlSender = () => {
	 let buttonId = event.target.id;
	 let pageTitle = document.title;
  	 let home = 'http://localhost:8080/';
  		var usrNme = "<%=request.getSession().getAttribute("user")%>";	 	
    	let usr = usrNme;
    		let clickData = '{action:Transition from page http://localhost:8080/index.jsp to page '+home+buttonId+'.jsp by user '+usr+'}';		

 
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
	 	var usrNme = "<%=request.getSession().getAttribute("user")%>";	 	
	    let usr = usrNme;

   	 let clickData2 = '{action:Button on page http://localhost:8080/'+pageTitle2+'.jsp clicked: '+buttonId2+' by user '+usr+'}';
   	 /* by user'+usrName */
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
 
 
 document.getElementById('logoutButton').addEventListener('click', function() {
	 	var usrNme = "<%=request.getSession().getAttribute("user")%>";	 	
	    const dataToSend = {  usrNme };

	    fetch('http://localhost:8080/logout', { 
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json'
	        },
	        body: JSON.stringify(dataToSend)
	    })
	    .then(response => response.json())
	    .then(data => console.log('Успех:', data))
	    .catch(error => console.error('Ошибка:', error));
	});
 
 
	function processClick(text, textId, buttonId, errorFieldId) {

		const input = document.getElementById(textId);
		const button = document.getElementById(buttonId);
		const errorField = document.getElementById(errorFieldId);
		errorField.style.display = 'none';
		if (input.disabled) {
			input.disabled = false;
			button.value = "Submit";
			input.focus();
			
		} else {			
			(async () => {
				  const result = await fetchData(textId,input.value);
				  if(result){
						input.disabled = true;
						button.value="Edit";
						errorField.textContent = "result is positive";
						input.value = "Correct responce";
				  }else{
					 	/* input.value = "Hello from Test"; */
					 	button.value="Submit";
						input.disabled = false;
						errorField.style.display = 'block';
						errorField.textContent = "result is negative";
				  }
				})();
			

		}

	}
	
	
	async function fetchData(key, value) {
		let obj = {
				[key]: value		//the way to create the json object basing on arguments above 
		}
		  try {
		    // 1. Ожидаем ответ сервера
		    const response = await fetch('http://localhost:8080/credentialsUpdate',{
		    	method: 'POST', 
		    		headers: {
		    		    'Content-Type': 'application/json'
		    		  },body: JSON.stringify(obj)
		    });
	        
		    
		    const data = await response.text();
		    const input = document.getElementById(key);
		    
		    
		    
		    /* alert(data); */
		    
		    // Проверка на ошибки HTTP (например, 404 или 403)
			 if(response.status == 401 || response.status == 403){
				
		    	/* alert(response.status+' '+value); */
		    	input.value = "Response from server: "+value;
		    	return false;
		    }else if(response.status == 200){
		    	alert(key+' updated');
		    	return true;
		    }
		    
		    
		  } catch (error) {
			  
		    console.error('Ошибка запроса:', error);
		    errorField.textContent = error;
		    return false;
		    
		  }
		  
		}	
 
 
</script>


</html>
</html>