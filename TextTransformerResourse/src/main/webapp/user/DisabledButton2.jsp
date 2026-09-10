<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Поле, изначально неактивное -->

<input type="text" id="myInput" value="Старый текст" disabled>


<!-- Кнопка для действия -->
<!-- <button type="button" id="myButton" button onclick="processData()">Отправить и обновить</button> -->

<button type="submit" id="myButton">Отправить и обновить</button>
				<div id="errorMessage"
					style="color: red; font-size: 12px; display: none;"></div>

<script>

/* document.getElementById('myInput').addEventListener('submit', function(event) {
    event.preventDefault(); // Запрещает перезагрузку страницы
    let textData = document.getElementById('myInput').value;
    alert(textData.value());
    
    	fetch('/credentials', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ text: textData })
    })
    .then(response => response.json())
    .then(data => console.log('Успех:', data))
    .catch(error => console.error('Ошибка:', error));
}); */



const button = document.getElementById('myButton');
button.addEventListener('click', function() {
	
    
    document.getElementById('myInput').disabled = false;
    if(document.getElementById('myInput').disabled==false){
    	/* alert('Поле открыто для редактирования'); */
    	processData();
    }
});

function processData() {
	/* alert('Process data method launched'); */
	var inputField = document.getElementById("myInput");
	/* inputField.value = "Новый текст: " + new Date().toLocaleTimeString(); */
	/* document.getElementById('myInput').disabled = true; */
    

	if(document.getElementById('myInput').disabled==false){
		const button2 = document.getElementById('myButton');

		//2. Добавляем слушатель событий
			button2.addEventListener('click', function() {
			alert('Кнопка была нажата!');
			});
			inputField.disabled = true;
			inputField.value ="Привет, Андрей";
	}	
	
	if(document.getElementById('myInput').disabled==false){
    	/* alert('Поле открыто для редактирования'); */
    	
		
    	fetch('/credentialsUpdate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ text: inputField.value })

        })
        .then(responce=>response.json())
        /* .then(data => {
            const textData = JSON.stringify(data) // Превращаем объект обратно в строку
            /* inputField.value = textData;     */
           
            
            /* console.log(textData); */
          /* }) */
        /* then(inputField.value=>responce.json()). */
        /* then(inputField.value=>responce). */
        .catch(error => console.error('Ошибка:', error));
    	/* alert('!!!!'); */
    	
        /* inputField.value ="Привет, Андрей"; */
    	
		/* var inputField = document.getElementById('myInput'); */
		/* alert(inputField.value); */
		//
		

		
		
		
    	
    }
	
	/* document.getElementById('myInput').addEventListener('button', async function(event) {
		inputField.value ="Привет, Андрей";
		
	}); */
}


</script>
</html>