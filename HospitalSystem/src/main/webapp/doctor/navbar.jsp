<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main navk">
    <div class="wrap">
        <ul class="menu">
            <li class="logo"><a href="index.jsp">Главная</a></li>
            <li><a href="patient.jsp">Пациент</a></li>
            <li>
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        ${doctorObj.fullName}
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="edit_profile.jsp">Редактировать профиль</a></li>
                        <li><a class="dropdown-item" href="../doctorLogout">Выход</a></li>
                    </ul>
                </div>
            </li>
        </ul>
    </div>
</div>