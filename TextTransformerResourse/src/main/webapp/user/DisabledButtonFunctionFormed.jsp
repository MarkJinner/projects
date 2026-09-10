<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<style>
.form-container {
	justify-content: center;
	align-items: left;
	display: flex;
	flex-direction: column; /* Поля под полями */
	gap: 100px; /* Расстояние между строками */
	
}

.form-row {
	width: 300px;
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
}.form-errorField{
	position: absolute;
	align-items: center; 
	text-align:center;
	top: 40px; 
}.styled-button {
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
}.placeholder {
	width: 350px;
	height: 30px;
	/* border-radius: 3px; */
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1); 
  	justify-content: flex-end;
  	background-color: lightgrey; 
}.placeholder:disabled {
	background-color: orange; 
   color: #888; /* Цвет по умолчанию */
   opacity: 1; /* Для Firefox */
}

/* Стиль для плейсхолдера в заблокированном поле */
.input:disabled::placeholder {
  color: red; /* Новый цвет для заблокированного состояния */
  opacity: 0.5; /* Дополнительная прозрачность (по желанию) */
}
</style>
<title>Insert title here</title>
</head>
<body>
	<center>
		<div class="form-container">
		<center>
			<div class="form-row" style="margin-right:50px">
				<label for="password">Password:</label> <input type="Text" style="width: 350px;"
					id="password" value="Очень старые данные" disabled class="placeholder"> 
					<input
					type="button" button id="actionButton1" value="Edit" button
					onclick="processClick('button 1 clicked','password','actionButton1','errorField1')" class="styled-button">
			<div class= "form-errorField" id="errorField1"
					style="color: red; font-size: 12px; display: none; margin-left:90px;margin-top:15px"></div>

			</div>
	<br>
							
			
			<div class="form-row">
				<label for="login">Login:</label> <input type="Text" id="login"
					value="Не такие старые данные" disabled class="placeholder"> <input
					type="button" button id="actionButton2" value="Edit" button
					onclick="processClick('button 2 clicked','login','actionButton2','errorField2')" class="styled-button">
				
											<div class= "form-errorField" id="errorField2"
					style="color: red; font-size: 12px; display: none; margin-left:65px;margin-top:85px"></div>
				
			</div>
	<br>
			<div class="form-row">
				<label for="email">Email:</label> <input type="Text" id="email"
					value="Не настолько старые данные" disabled class="placeholder"> <input
					type="button" button id="actionButton3" value="Edit" button
					onclick="processClick('button 3 clicked','email','actionButton3','errorField3')"class="styled-button">
				<div class= "form-errorField" id="errorField3"
					style="color: red; font-size: 12px; display: none; margin-left:65px;margin-top:160px"></div>
			</div>
			<center>
		</div>

	</center>


	<script>
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
							button.value="Submit";
							input.disabled = false;
							errorField.style.display = 'block';
							errorField.textContent = "result is negative";
					  }
					})();
				

			}

		}
		
		
		async function fetchData(key, value) {
			let obj ={
					[key]: value		//the way to create the json object basing on arguments above 
					};  
			
			  try {
			    // 1. Ожидаем ответ сервера
			    const response = await fetch('http://localhost:8080/credentialsUpdate',{
			    	method: 'POST', 
			    		headers: {
			    		    'Content-Type': 'application/json'
			    		  /* },body: JSON.stringify({password: key+' '+value }) */
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
			    	/* alert(response.status+' success'); */
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
