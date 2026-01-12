<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
	margin:3px;

}

.styled-button3 {
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
	text-align: justify;
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
	margin-top: 4px;
	/* Подвал прижимается к низу, занимая оставшееся пространство */
	background-color: #f1f1f1;
	padding: 20px;
	text-align: center;
	background-color: lightblue;
}

#modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgba(0, 0, 0, 0.5); /* Black w/ opacity */
	justify-content: center; /* Center horizontally */
	align-items: center; /* Center vertically */
}

.modal-content {
	background-color: #fefefe;
	margin: auto; /* Auto margin centers it */
	padding: 20px;
	border: 1px solid #888;
	width: 80%; /* Could be more specific */
	max-width: 500px; /* Max width for larger screens */
	position: relative;
	font-family: PT Sans;
	font-weight: normal;
	border-radius: 8px;
} /* Close button */
.close-button {
	color: #black;
	float: center;
	font-size: 28px;
	font-weight: bold;
	position: absolute;
	top: 10px;
	right: 15px;
	cursor: pointer;
}

.close-button:hover, .close-button:focus {
	color: orange;
	text-decoration: none;
	cursor: pointer;
}

.translationForm {
	/* max-width: 1000px; */
	font-family: PT Sans;
	display: flex;
	justify-content: space-around;
	font: 12px;
	margin-top: 4px;
	background-color: lightblue;
	position: relative;
	transition: transform 0.3s ease;
	
	
}
textarea{
	width: 80%; /* Занимает 100% ширины контейнера */
    padding: 10px;
    /* font-size: clamp(14px, 3vw, 18px); /* Плавное изменение размера шрифта */
    font-size: 14px;
    box-sizing: border-box; /* Padding и border вписываются в ширину */
    resize: none; /* Отключаем ручное изменение размера */
    min-height: 80px;
    
	}

.form {
  width: 100%;
  box-sizing: border-box;
  height: 100vh;
  display: flex;
  justify-content: space-between;
}

#errorModal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	top: 40%;
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgba(0, 0, 0, 0.5); /* Black w/ opacity */
	justify-content: center; /* Center horizontally */
	align-items: center; /* Center vertically */
}

.errorModal-content {
	background-color: #fefefe;
	margin: auto; /* Auto margin centers it */
	top: 40%;
	padding: 20px;
	border: 1px solid #888;
	width: 80%; /* Could be more specific */
	max-width: 500px; /* Max width for larger screens */
	position: relative;
	font-family: PT Sans;
	font-weight: normal;
	border-radius: 8px;
} /* Close button */
.errorClose-button {
	color: #black;
	float: center;
	font-size: 28px;
	font-weight: bold;
	position: absolute;
	top: 10px;
	right: 15px;
	cursor: pointer;
}

.errorClose-button:hover, .errorClose-button:focus {
	color: orange;
	text-decoration: none;
	cursor: pointer;
}
</style>
<head>
<meta charset="UTF-8">
<title>toUpperCase</title>
</head>
<body>
	<center>

		<header>
			<div class="c">
				<a class="styled-button" href="http://localhost:8080/index.jsp"
					id="index">MAIN</a>
				<div class="dropdown">
					<button class="styled-button2" id="menu">MENU</button>
					<div class="dropdown-content">
						<a class="styled-button"
							href="address/punctuationMarksRemover.html">PunctuationMarksRemover</a>
						<a class="styled-button"
							href="address/toLowerCaseTransformer.html">ToLowerCaseTransformer</a>
						<a class="styled-button" href="address/lineBreaksRemover.html">LineBreaksRemover</a>
						<a class="styled-button"
							href="address/toCapitalFirstLetterTransformer.html">ToCapitalFirstLetterTransformer</a>
						<a class="styled-button" href="http://localhost:8080/default.jsp">ajaxSample</a>
						<a class="styled-button"
							href="http://localhost:8080/fetchSample.jsp">fetchSample</a> <a
							class="styled-button"
							href="http://localhost:8080/browserToServerToBrowserFetchSample.jsp">browserToServerToBrowserFetchSample</a>
					</div>
				</div>
				<a class="styled-button" href="http://localhost:8080/about.jsp">ABOUT
					US</a> <a
					href="mailto:oi243012@gmail.com?subject=Letter topic&body=Text of the letter"
					class="styled-button2" id="writeUs">WRITE US</></a>
				<button class="styled-button" style="float: right;" ; button
					onclick="window.location.href = 'http://localhost:8080/ru/';">RU</button>
				</a>
		</header>
	</center>
	<div class="text-box">This is pretty simple service, which allows
		you to transform all your text into text in upper case. Here you can
		paste needed text or download it in file in .txt, .doc, .docx or pages
		format. Make sure your file do not contains table. After converting
		your text into needed, you can copy it to clipboard or download as
		.txt.</div>

	
	





	<div id="modal">
		<div class="modal-content">
			<span class="close-button">&times;</span>
			<center>
				<h2>Well done!</h2>
				<center>
					<p>Your transformed text copied to clipboard</p>
		</div>
	</div>

	<div id="errorModal">
		<div class="errorModal-content">
			<span class="errorClose-button">&times;</span>
			<center>
				<h2>Error!</h2>
				<center>
					<p>Uploaded file not supported. You can upload .txt, .doc or
						.docx</p>
		</div>
	</div>
	
	<div class="translationForm">

		<div class="content-box">
			<p>
			<form action="toUpperCase" form id="myInitForm" method="POST">
				<label for="originalText"><center>Original text:</label><br>
				<textarea id="originalText" name="originalText" rows="8" cols="60"
					placeholder="Input your text"></textarea>
				<p>
				<center>
					<input type="submit" class="styled-button2" id="transformButton"
						value="Transform text"> <input type="file" id="fileInput"
						class="styled-button3" style="display: none;"> <input
						type="submit" class="styled-button2" id="uploadButton"
						value="Upload file">
			</form>


		</div>

		<div class = "content-box">
			<p>
			<form id="myForm" method="post">
				<label for="copyToClipBoard"><center>Transformed
						text:</label><br>
				<textarea id="outputField" name="outputField" rows="8" cols="60"
					placeholder="Here will be transformed text"></textarea>
				<p>
				<center>
					<input type="submit" id="openModalBtn" class="styled-button2"
						value="Copy transformed text">
					<input type="button" id="saveButton" class="styled-button2"value="Save as .txt">
			</form>

			</p>

		</div>


	</div>


	<div class="text-box">

		<h3>SITE TextTransformer UNDER CONSTRUCTION</h3>
		<p>byOlegov
	</div>


	<script>
	
	const form2 = document.getElementById('myForm');//ajax form
	const responseDiv2 = document.getElementById('response');
	const ignoreButton = document.getElementById('saveButton');
	
	ignoreButton.addEventListener('click', function(event) {
	    event.stopPropagation(); // Предотвращает всплытие события к родительской форме
	    // Можно добавить 
	    /* alert('Кнопка игнорируется!'); */
	});

	form2.addEventListener('submit', async (event) => {
	event.preventDefault(); // Предотвращаем стандартную отправку формы

	const formData2 = new FormData(form2); // Создаем объект FormData из формы


	try {
	    const response2 = await fetch('http://localhost:8080/copyToClipboard', { // Замените на ваш серверный URL
	        method: 'POST',
	        body: formData2 // Отправляем данные в формате FormData
	    });

	    if (!response2.ok) {
	    	
	        throw new Error(`HTTP error! status: ${response2.status}`);
	    }else if(response2.ok){
	    	
	    	form2.reset();
	    }

	} catch (error) {
	    responseDiv.innerHTML = `Ошибка: ${error}`; // Отображаем ошибку
	}
	});

const openModalBtn = document.getElementById('openModalBtn');
const modal = document.getElementById('modal');
const closeButton = document.querySelector('.close-button');

openModalBtn.onclick = function() {
    modal.style.display = 'flex'; // Use 'flex' to center content
}

closeButton.onclick = function() {
    modal.style.display = 'none';
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = 'none';
    }
}

function showErrorModal() {
    const modal = document.getElementById('errorModal');
    modal.style.display = 'block';  
    const errorCloseButton = document.querySelector('.errorClose-button');
    errorCloseButton.onclick = function() {
    modal.style.display = 'none';
    }
   
}



const form = document.getElementById('myInitForm');//ajax form
form.addEventListener('submit', async (event) => {
event.preventDefault(); // Предотвращаем стандартную отправку формы

const formData = new FormData(form); // Создаем объект FormData из формы
formData.append('transformer',form.action);
form.reset();

try {
    const response = await fetch('http://localhost:8080/transform', { // Замените на ваш серверный URL
        method: 'POST',
        body: formData // Отправляем данные в формате FormData
    });

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    const result = await response; // Или response.json() если сервер возвращает JSON
    /* responseDiv.innerHTML = await response.text(); */ // Отображаем ответ(если нужно отобразить на той же странице снизу)
    const data = await response.text();
    /* responseDiv.innerHTML = data; */
  	document.getElementById('outputField').value = data;
	

} catch (error) {
    responseDiv.innerHTML = `Ошибка: ${error}`; // Отображаем ошибку
}
});





document.getElementById('uploadButton').addEventListener('click', function() {
    document.getElementById('fileInput').click();
});

document.getElementById('fileInput').addEventListener('change', function() {
	event.preventDefault();
    const file = this.files[0]; // Получаем первый выбранный файл
    
    if (file) {
        // Создаем объект FormData
        const formData = new FormData();
        formData.append('file', file); // Добавляем файл в форму

        // Отправляем на сервер с помощью fetch API
        const response =  fetch('http://localhost:8080/upload', { // `/upload` - ваш URL сервлета
            method: 'POST',
            body: formData,
            enctype: 'multipart/form-data'

        })

        .then(response => response.text())
        .then(text => {document.getElementById('originalText').value = text;
        // Здесь мы работаем с текстом, который уже готов
        
        if(text=="Uploaded file not supported"){
        	showErrorModal();
        }
        })
			.catch(error => {
            console.error('Ошибка:', error);
        }); 
        
        
        
    }
    
});


const urlSender = () => {
	 let buttonId = event.target.id;
	 let pageTitle = document.title;
 	 let home = 'http://localhost:8080/';
  	 let clickData = '{action:Transition from page http://localhost:8080/'+pageTitle+'.jsp to page '+home+buttonId+'.jsp'+'}';
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

const resultForm = document.getElementById('myForm');
document.getElementById('saveButton').addEventListener('click', function() {
    // 1. Получаем текст из <textarea>
    const textToSave = document.getElementById('outputField').value;

    // 2. Создаем Blob с текстом и типом 'text/plain'
    // Blob — это объект, который представляет данные в виде файла.
    const blob = new Blob([textToSave], { type: 'text/plain' });

    // 3. Создаем временную ссылку для скачивания
    // Мы создаем URL для нашего Blob.
    const url = URL.createObjectURL(blob);

    // 4. Создаем скрытый <a> элемент для скачивания
    const a = document.createElement('a');
    a.href = url;
    a.download = 'my_text_file.txt'; // Имя файла

    // 5. Добавляем элемент в DOM (невидимо) и имитируем клик
    document.body.appendChild(a);
    a.click();

    // 6. Удаляем временные элементы и URL после скачивания
    document.body.removeChild(a);
    URL.revokeObjectURL(url); // Освобождаем память
    resultForm.reset();
});

</script>
	<div class="footer">All rights reserved @byOlegov 2026</div>
</body>
</html>