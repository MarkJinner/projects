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
	margin-top: 5px;
	/* Подвал прижимается к низу, занимая оставшееся пространство */
	background-color: #f1f1f1;
	padding: 20px;
	text-align: center;
	background-color: lightblue;
}

.credentials-box {
	width: 300px;
	border-bottom: 5px solid #ccc;
	border-top: 5px solid #ccc;
	border-left: 5px solid #ccc;
	border-right: 5px solid #ccc;
	padding-bottom: 10px;
	padding-top: 20px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
	justify-content: center;
}

.placeholder {
	width: 150px;
	height: 30px;
	 /* border-radius: 3px; */
	 box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.forget-button {
	font-weight: normal;
	font-size: 12px;
	border: none;
	background-color: transparent;
	cursor: pointer;
	text-decoration: none;
}

.forget-button:hover {
	color: orange;
	z-index: 1;
	background-color: transparent; /* Цвет при наведении */
	text-decoration: underline;
}


.overlay {
    /* Hidden by default */
    display: none; 
    position: fixed; 
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; 
    height: 100%; 
    background-color: rgba(0,0,0,0.7); /* Black background with opacity */
    justify-content: center;
    align-items: center;
}

.popup-content {
    background-color: lightblue;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
    height: 350px;
    max-width: 350px;
    position: relative;
    border-radius: 5px;
    border-top: 5px solid #ccc;
    border-left: 5px solid #ccc;
	border-right: 5px solid #ccc;
	border-bottom: 5px solid #ccc;
	padding-bottom: 10px;
}

.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
    cursor: pointer;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

form label {
    display: block;
    margin-top: 10px;
    text-align: left;
}

.form input, form button {
    width: 300px;
    padding: 8px;
    margin-top: 5px;
    box-sizing: border-box; /* Ensures padding doesn't affect width */
}

.form button {
    background-color: #4CAF50;
    color: white;
    border: none;
    cursor: pointer;
    margin-top: 15px;
}


</style>

<title>Login</title>
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
					class="styled-button2" id="writeUs">WRITE US</> </a>
				<button class="styled-button"
					style="float: right; margin-top: 0.5px" ; button
					onclick="window.location.href = 'http://localhost:8080/ru/index.jsp';"
					id="ru/index">RU</button>
				</a>
		</header>
	</center>
	<div class="text-box" style="height: 450px">
		<center>
			<div class="credentials-box">
				<div id="errorMessage"
					style="color: red; font-size: 12px; display: none;"></div>
				<p>
				<form id="credentials" method="POST">
					<!-- Поля для email и password -->
					<input type="text" class="placeholder" placeholder="Login"
						id="login" name="login" required>
					<p>
						<input type="password" class="placeholder" placeholder="Password"
							id="password" name="password" required>
					</p>
					<p>
						<button type="submit" class="styled-button2" style="width:100px;'">Submit</button>
					<p>
						<input type="button" class="forget-button" value="Registration"
							button onclick="openPopup()">
						<input type="button" class="forget-button"
							value="Forgot password?" button
							onclick="window.location.href = 'http://localhost:8080/forgotPassword.jsp'">
				</form>

				<br>
				
	 <div id="popupOverlay" class="overlay">
        <div class="popup-content">
            <span class="close" onclick="closePopup()">&times;</span> <!-- Close button -->
            <h2>Registration Form</h2>
            		<div id="registrationErrorMessage"
					style="color: red; font-size: 12px; display: none;"></div>
            <form id="userRegistration"method="post">
            <!-- <form action="registration" id="userRegistration"method="post"> -->
				<p><input type="email" class="placeholder" style="width:300px" placeholder="email" id="email" name="email" required></br>
                <p><input type="login" class="placeholder" style="width:300px" placeholder="login" id="login" name="login" required></br>
				<p><input type="password" class="placeholder" style="width:300px" placeholder="password" id="password" name="password" required></br>
                <p>
                <p><button type="submit" class="styled-button" style="width:100px">Sign Up</button>
            </form>
        </div>
    </div>
				
			</div>
	</div>

	<div class="footer">All rights reserved @byOlegov 2026</div>


</body>
<script>
document.getElementById('credentials').addEventListener('submit', async function(event) {
    event.preventDefault(); // Предотвращаем стандартную отправку формы

    const form = event.target;
    const formData = new FormData(form);
    const errorMessageDiv = document.getElementById('errorMessage');
    errorMessageDiv.style.display = 'none'; // Скрываем сообщение перед новой попыткой
/* 	alert("!"); */
    try {
        const response = await fetch('/credentials', { // Замените на ваш URL API
            method: 'POST',
            body: formData
        });

        if (response.ok) {
            // Успешный вход: перенаправление или что-то еще
            console.log('Вход успешен!');
            /* alert('Вход успешен!'); */
            window.location.href = '/user/index.jsp';
        } else {
            // Ошибка! Сервер вернул статус не 2xx
            const errorData = await response.json(); // Предполагаем, что сервер возвращает JSON
            let message = 'Произошла ошибка при входе.';

            if (response.status == 401 || response.status == 403) {
            	 /* alert(errorData.error); */
            	/* console.log('Вход неуспешен!'); */
                /* message = errorData.message || 'Неверный логин или пароль'; */
                message = errorData.error || 'Неверный логин или пароль';
                form.reset();
            } else {
                message = errorData.message || `Ошибка ${response.status}`;
            }

            errorMessageDiv.textContent = message; // Выводим сообщение
            errorMessageDiv.style.display = 'block'; // Показываем блок
        }
    } catch (error) {
        // Сетевая ошибка или ошибка сервера до получения ответа
        console.error('Ошибка сети или сервера:', error);
        errorMessageDiv.textContent = 'Нет соединения с сервером или произошла внутренняя ошибка.';
        errorMessageDiv.style.display = 'block';
    }
});


var popup = document.getElementById("popupOverlay");

//Function to open the popup
function openPopup() {
 popup.style.display = "flex"; // Use "flex" to center content
}

//Function to close the popup
function closePopup() {
 popup.style.display = "none";
}

//Optional: Close the popup if the user clicks outside of the content
window.onclick = function(event) {
 if (event.target == popup) {
     popup.style.display = "none";
 }
}


	document.getElementById('userRegistration').addEventListener('submit', async function(event) {
    event.preventDefault(); // Предотвращаем стандартную отправку формы

    const form2 = event.target;
    const formData2 = new FormData(form2);
    const errorMessageDiv2 = document.getElementById('registrationErrorMessage');
    errorMessageDiv2.style.display = 'none'; // Скрываем сообщение перед новой попыткой
    /* alert("!"); */
    try {
        const response2 = await fetch('/userRegistration', { // Замените на ваш URL API
            method: 'POST',
            body: formData2
        });

        if (response2.ok) {
            // Успешный вход: перенаправление или что-то еще
            console.log('Вход успешен!');
            /* alert('Вход успешен!'); */
            window.location.href = '/user/index.jsp';
        } else {
        	
            // Ошибка! Сервер вернул статус не 2xx
            const errorData2 = await response2.json(); // Предполагаем, что сервер возвращает JSON
            let message2 = 'Произошла ошибка при входе.';
            /* alert(errorData2); */
            if (response2.status == 401 || response2.status == 403) {
            	  /* alert('401'); */
            	/* console.log('Вход неуспешен!'); */
                /* message = errorData.message || 'Неверный логин или пароль'; */
                message2 = errorData2.error || 'Неверный логин или пароль';
                
                form2.reset();
            } else {
                message2 = errorData2.message || `Ошибка ${response.status}`;
            }

            errorMessageDiv2.textContent = message2; // Выводим сообщение
            errorMessageDiv2.style.display = 'block'; // Показываем блок
        }
    } catch (error) {
        // Сетевая ошибка или ошибка сервера до получения ответа
        console.error('Ошибка сети или сервера:', error);
        errorMessageDiv2.textContent = 'Нет соединения с сервером или произошла внутренняя ошибка.';
        errorMessageDiv2.style.display = 'block';
    }
});

</script>
</html>