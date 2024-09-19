<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container">
    <jsp:include page="components/header.jsp"/>
    <div class="wrap height">

        <form action="your_notebook.jsp" method="get" class="form-notebook-config">

            <div class="form-notebook-config__element">
                <div>
                    <label for="makerId">Производитель: </label>
                </div>
                <div>
                    <select name="maker" id="makerId">
                        <option label="Выберите производителя" value="" disabled selected hidden></option>
                        <option value="Lenovo">Lenovo</option>
                        <option value="LG">LG</option>
                        <option value="Huawei">Huawei</option>
                        <option value="ASUS">ASUS</option>
                        <option value="Samsung">Samsung</option>
                        <option value="MSI">MSI</option>
                        <option value="Xiaomi">Xiaomi</option>
                        <option value="Apple">Apple</option>
                    </select>
                </div>
            </div>

            <div class="form-notebook-config__element">
                <div>
                    <label for="ssdSizeId">Объем SSD: </label>
                </div>
                <div>
                    <input type="range" name="ssdSize" id="ssdSizeId" min="128" max="4096" value="128" step="128"
                           oninput="this.nextElementSibling.value = this.value">
                    <output>128</output>ГБ
                </div>
            </div>

            <div class="form-notebook-config__element">
                <div>
                    Тип видеокарты:
                </div>
                <div>
                    <label for="integratedGpuId">
                        <input type="radio" name="gpuType" id="integratedGpuId" value="Интегрированная"> Интегрированная
                    </label>
                    <br>
                    <label for="discreteGpuId">
                        <input type="radio" name="gpuType" id="discreteGpuId" value="Дискретная"> Дискретная
                    </label>
                </div>
            </div>

            <div class="form-notebook-config__element">
                <div>
                    <label for="cpuModelId">Модель процессора: </label>
                </div>
               <div>
                   <select name="cpuModel" id="cpuModelId">
                       <option label="Выберите ЦП" value="" selected disabled hidden=""></option>
                       <option value="Intel Core i9−14900K">Intel Core i9−14900K</option>
                       <option value="AMD Ryzen 9 7950X3D">AMD Ryzen 9 7950X3D</option>
                       <option value="AMD Ryzen 7 5800X3D">AMD Ryzen 7 5800X3D</option>
                       <option value="Intel Core i7−14700K">Intel Core i7−14700K</option>
                       <option value="AMD Ryzen 5 5600X">AMD Ryzen 5 5600X</option>
                       <option value="Intel Core i5-12400F">Intel Core i5-12400F</option>
                       <option value="Intel Core i5−13600K">Intel Core i5−13600K</option>
                   </select>
               </div>
            </div>

            <div class="form-notebook-config__element">
                <div>
                    Операционная система:
                </div>
                <div>
                    <label for="os-windows10">
                        <input type="radio" name="OS" id="os-windows10" value="Windows 10"> Windows 10
                    </label>
                    <br>
                    <label for="os-windows11">
                        <input type="radio" name="OS" id="os-windows11" value="Windows 11"> Windows 11
                    </label>
                    <br>
                    <label for="os-ubuntu22.04">
                        <input type="radio" name="OS" id="os-ubuntu22.04" value="Ubuntu 22.04"> Ubuntu 22.04
                    </label>
                    <br>
                    <label for="os-linux_mint21">
                        <input type="radio" name="OS" id="os-linux_mint21" value="Linux Mint 21"> Linux Mint 21
                    </label>
                </div>
            </div>

            <div class="form-notebook-config__element">
                <div>
                    Тип матрицы:
                </div>
                <div>
                    <label for="matrixTN">
                        <input type="radio" name="matrixType" id="matrixTN" value="TN"> TN
                    </label>
                    <br>
                    <label for="matrixIPS">
                        <input type="radio" name="matrixType" id="matrixIPS" value="IPS"> IPS
                    </label>
                </div>
            </div>

            <div class="form-notebook-config__element">
                <div>
                    <label for="screenResolutionId">Разрешение экрана: </label>
                </div>
                <div>
                    <select name="screenResolution" id="screenResolutionId">
                        <option label="Выберите разрешение экрана" value="" selected disabled hidden=""></option>
                        <option value="1366x768">1366x768</option>
                        <option value="1600x900">1600x900</option>
                        <option value="1920x1080">1920x1080</option>
                        <option value="1920x1200">1920x1200</option>
                        <option value="2560x1600">2560x1600</option>
                        <option value="2560x1664">2560x1664</option>
                        <option value="3024x1964">3024x1964</option>
                        <option value="3456x2234">3456x2234</option>
                    </select>
                </div>
            </div>

            <div class="form-notebook-config__element">
                <div>
                    <label for="sensorId">Сенсорный экран: </label>
                    <input type="checkbox" name="sensor" id="sensorId">
                </div>
                <div>
                    <label for="keyboardLightId">Подсветка клавиатуры: </label>
                    <input type="checkbox" name="keyboardLight" id="keyboardLightId">
                </div>
            </div>

            <button type="submit">Отправить</button>
            <input type="reset" value="Очистить">
        </form>
    </div>
    <jsp:include page="components/footer.jsp"/>
</div>


</body>
</html>
