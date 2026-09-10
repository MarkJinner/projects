<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Downloads page</title>
</head>
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
	heigth: auto vh;
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
}

.placeholder:disabled {
	width: 350px;
	height: 30px;
	/* border-radius: 3px; */
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
	background-color: lightgrey;
	color: #888; /* Цвет по умолчанию */
	opacity: 1; /* Для Firefox */
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
	background-color: rgba(0, 0, 0, 0.7);
	/* Black background with opacity */
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
	justify-content: center;
}

.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
	cursor: pointer;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
.sortable-table {
  width: 100%;
  border-collapse: collapse;
  font-family: sans-serif;
}

.sortable-table th {
  background-color: #2c3e50;
  color: white;
  padding: 12px 15px;
  cursor: pointer;
  text-align: left;
  transition: background-color 0.2s;
  user-select: none; /* Запрещает выделение текста при клике */
}

.sortable-table th:hover {
  background-color: #34495e;
}

.sortable-table td {
  padding: 12px 15px;
  border-bottom: 1px solid #ddd;
}

.sortable-table tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}

.sortable-table th span {
  margin-left: 8px;
  font-size: 0.8em;
  opacity: 0.5;
}
.sortable-table th.asc span { content: '↑'; opacity: 1; }
.sortable-table th.desc span { content: '↓'; opacity: 1; }

</style>
<center>
	<%@ page import="com.gmail.database.*"%>
	<%@ page import="java.util.Date"%>
	<%@ page import="com.gmail.database.downloads.*"%>

	<header>
		<div class="c">
			<a class="styled-button2" href="http://localhost:8080/user/index.jsp"
				id="index">MAIN</a>
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

			<button class="styled-button" style="float: right; margin-top: 0.5px"
				; button
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
					<a class="styled-button" href="http://localhost:8080/index.jsp"
						id="logoutButton">Logout</a>

				</div>
			</div>
			</a>
	</header>
</center>
<body>
<main class="content">
<div class="text-box"
	style="height: 20vh; text-align: left; background-color: light-orange;">
	<div class="form-row" style="padding-left: 20px; margin-top: -20px;">
		<h3>User's downloads and transformations</h3>
	</div>
	<%-- <%=request.getSession().getAttribute("user")%> --%>
</div>
<div class="text-box"
	style="height: auto; text-align: left; padding-left: 20px;">

	<%String userName = request.getSession().getAttribute("user").toString();%>
	<%User user = new UsersDatabase().findByLogin(userName).get();%>
	<%DownloadsList list = new DownloadsList();%>



		

<table class="sortable-table">
  <thead>
    <tr>
      <th data-type="text">Date <span>⇅</span></th>
      <th data-type="text">Time <span>⇅</span></th>
      <th data-type="text">Transformer <span>⇅</span></th>
      <th data-type=number>Text length <span>⇅</span></th>
      <th data-type=link>Link <span>⇅</span></th>
    </tr>
  </thead>
  	<%=list.getFormattedDownloadsList(user)%>
</table>

	</div>
</div>
</body>
</main>
<center>
	
</center>

<footer class="footer">All rights reserved @byOlegov 2026</div>
<script>

document.querySelectorAll('.sortable-table th').forEach(headerCell => {
  headerCell.addEventListener('click', () => {
    const tableElement = headerCell.closest('table');
    const headerIndex = Array.prototype.indexOf.call(headerCell.parentElement.children, headerCell);
    const currentIsAscending = headerCell.classList.contains('asc');

    // Сброс стрелочек на других заголовках
    tableElement.querySelectorAll('th').forEach(th => {
      th.classList.remove('asc', 'desc');
    });

    // Установка направления текущей сортировки
    headerCell.classList.toggle('asc', !currentIsAscending);
    headerCell.classList.toggle('desc', currentIsAscending);

    const direction = currentIsAscending ? 1 : -1;
    const tBody = tableElement.querySelector('tbody');
    const rows = Array.from(tBody.querySelectorAll('tr'));

    // Функция для сортировки
    rows.sort((rowA, rowB) => {
      const cellA = rowA.querySelectorAll('td')[headerIndex].textContent.trim();
      const cellB = rowB.querySelectorAll('td')[headerIndex].textContent.trim();

      const dataType = headerCell.getAttribute('data-type');
      if (dataType === 'number') {
        return (parseFloat(cellA) - parseFloat(cellB)) * direction;
      } else {
        return cellA.localeCompare(cellB) * direction;
      }
    });

    // Перерисовка отсортированной таблицы
    tBody.innerHTML = '';
    rows.forEach(row => tBody.appendChild(row));
  });
});
</script>
</html>