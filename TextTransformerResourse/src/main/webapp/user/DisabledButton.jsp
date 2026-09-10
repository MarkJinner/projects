<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
Password
<input type="Text" id="dataInput" value="Очень старые данные" disabled>
<input type="button" button id="actionButton" value="Edit">
<p id="errorField" style="color: red;"></p>
</center>
<script>
  const input = document.getElementById('dataInput');
  const button = document.getElementById('actionButton');
  const errorField = document.getElementById('errorField');
  
  button.addEventListener('click', async () => {
    if (input.disabled) {
    	button.value="Submit";
      // Активация поля
      input.disabled = false;
      
      input.focus();
      errorField.textContent = ''; // Сброс ошибки
      
    } else {
      // Отправка запроса
      try {
        errorField.textContent = 'Отправка...';
        // Имитация запроса (замените на реальный fetch)
/*         const response = await new Promise((resolve, reject) => 
          setTimeout(() => Math.random() > 0.5 ? resolve('Новые данные') : reject('Ошибка сервера'), 1000)
        ); */
        const response = await fetch('http://localhost:8080/credentialsUpdate', { // Замените на ваш серверный URL
            method: 'POST',
            body: JSON.stringify({ password: dataInput.value })
        });
        
        if (response.status == 401 || response.status == 403) {
        	alert(response.status);
        }
        const data = await response.text();
        
       
        input.value = data;
        input.disabled = true; // Блокировка при успехе
        button.value="Edit";
        errorField.textContent = '';
      } catch (error) {
        errorField.textContent = error; // Показ ошибки
        input.disabled = false; // Поле остается активным
      }
    }
  });
</script>
</html>