<%@ page import="java.sql.Connection" %>
<%@ page import="com.db.DBConnect" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Колледж</title>
    <jsp:include page="component/allcss.jsp" />
</head>
<body>
<jsp:include page="component/navigation_menu.jsp" />

<% Connection con = DBConnect.getConn(); %>

<div id="carouselExampleIndicators" class="carousel slide">
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="img/slide_01.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img src="img/slide_02.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img src="img/slide_03.jpg" class="d-block w-100" alt="...">
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>

<section class="advantages">
    <div class="wrap">
        <h2>Государственное профессиональное образовательное учреждение «Сыктывкарский гуманитарно-педагогический
            колледж имени И.А. Куратова»</h2>
        <div class="advantages__block">
            <div class="advantages__element-left">
                <div>
                    <h4>Поступление</h4>
                    <p>Выпускникам 9 и 11 классов для поступления в колледж необходим лишь средний балл аттестата</p>
                </div>
                <div>
                    <h4>Вступительные испытания</h4>
                    <p>Выпускники 11 класса могут сдать ЕГЭ только по базовой математике и русскому языку
                        (этого достаточно для получения аттестата)</p>
                </div>
                <div>
                    <h4>ВУЗ и ЕГЭ</h4>
                    <p>Для поступления в вуз после колледжа не обязательно сдавать ЕГЭ, можно сдать экзамены в вузе</p>
                </div>
                <div>
                    <h4>Профессия</h4>
                    <p>Выпускники колледжей раньше начинают профессиональную деятельность</p>
                </div>
            </div>
            <div class="advantages__element-right">
                <img src="img/students.jpg" alt="">
            </div>
        </div>
    </div>
</section>

<section class="teachers">
    <div class="wrap">
        <h2>Наши преподаватели</h2>
        <div class="teachers__block">
            <div class="teachers__element">
                <img src="img/teacher_01.jpg" alt="">
                <h3>Пономарев Сергей Петрович</h3>
                <p>Преподаватель математики</p>
            </div>
            <div class="teachers__element">
                <img src="img/teacher_02.jpg" alt="">
                <h3>Шандаров Николай Владимирович</h3>
                <p>Преподаватель физики</p>
            </div>
            <div class="teachers__element">
                <img src="img/teacher_03.jpg" alt="">
                <h3>Иванов Иван Иванович</h3>
                <p>Преподаватель информатики</p>
            </div>
            <div class="teachers__element">
                <img src="img/teacher_04.jpg" alt="">
                <h3>Кловьев Христиан Жан-Мариевич</h3>
                <p>Преподаватель французского языка</p>
            </div>
        </div>
    </div>
</section>

<jsp:include page="component/footer.jsp" />
</body>
</html>