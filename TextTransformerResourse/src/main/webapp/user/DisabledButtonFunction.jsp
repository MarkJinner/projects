<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<style>
.container {
	align-items: center;
	font-family: PT Sans;
	font-weight: normal;
	font-size: 14px;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<center>
		<div>
		<div class="container">
			Password <input type="Text" id="password" value="Очень старые данные"
				disabled style="width: 250px;"> <input type="button" button
				id="actionButton1" value="Edit" button
				onclick="processClick('button 1 clicked','password','actionButton1','errorField1')">
			<p> <id="errorField1" style="color: red;"></p>
			Login <input type="Text" id="login" value="Не такие старые данные"
				disabled style="width: 250px;"> <input type="button" button
				id="actionButton2" value="Edit" button
				onclick="processClick('button 2 clicked','login','actionButton2','errorField2')">
			<p> <id="errorField2" style="color: red;"></p>
		</div>
		</div>
	</center>
	<script>
		function processClick(text, textId, buttonId, errorFieldId) {

			const input = document.getElementById(textId);
			const button = document.getElementById(buttonId);
			const errorField = document.getElementById(errorFieldId);
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
							input.value = "Correct responce";
					  }else{
							input.disabled = false;
							/* input.value = "Incorrect responce"; */
					  }
					})();
				

			}

		}
		
		
		async function fetchData(key, value) {
			  try {
			    // 1. Ожидаем ответ сервера
			    const response = await fetch('http://localhost:8080/credentialsUpdate',{
			    	method: 'POST', 
			    		headers: {
			    		    'Content-Type': 'application/json'
			    		  },body: JSON.stringify({text: key+' '+value })
			    });
		        
			    
			    const data = await response.text();
			    
			    alert(data);
			    
			    // Проверка на ошибки HTTP (например, 404 или 500)
				 if(response.status == 401 || response.status == 403){
			    	alert(response.status+' '+value);
			    	value = data;
			    	return false;
			    }else if(response.status == 200){
			    	alert(response.status+' success');
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


<!--   const input = document.getElementById('dataInput');
  const button = document.getElementById('actionButton');
  
  
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
  }); -->