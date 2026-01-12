<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
  /* Скрываем чекбокс и саму форму изначально */
  input[type="checkbox"] {
    display: none;
  }
  .form-container {
    display: none; /* Форма скрыта */
    position: fixed;
    top: 50%; left: 50%;
    transform: translate(-50%, -50%);
    padding: 20px;
    background: white;
    border: 1px solid #ccc;
    box-shadow: 0 0 10px rgba(0,0,0,0.2);
    z-index: 100;
  }
  /* Когда чекбокс отмечен, показываем форму */
  input[type="checkbox"]:checked ~ .form-container {
    display: block;
  }
  /* Стилизация "кнопки" */
  .open-form-button {
    padding: 10px 20px;
    background-color: #007bff;
    color: white;
    border: none;
    cursor: pointer;
    border-radius: 5px;
    font-size: 16px;
  }
  .open-form-button:hover {
    background-color: #0056b3;
  }
</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="checkbox" id="show-form">

<!-- Кнопка (на самом деле <label> для чекбокса), при клике на которую чекбокс будет отмечен -->
<label for="show-form" class="open-form-button">Нажмите, чтобы открыть форму</label>

<!-- Сама форма, которая будет скрыта/показана -->
<div class="form-container">
  <h3>Форма</h3>
  <form>
    <label for="name">Имя:</label><br>
    <input type="text" id="name" name="name"><br><br>
    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email"><br><br>
    <button type="submit">Отправить</button>
  </form>
  <!-- Кнопка для закрытия (также через label) -->
  <label for="show-form" style="display: block; margin-top: 15px; cursor: pointer; color: red;">Закрыть</label>
</div>

</body>
</html>