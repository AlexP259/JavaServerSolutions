"use strict";

function updateActive(selectedRadio) {
    // Удаляем класс active у всех меток
    let labels = document.querySelectorAll('.list-group-item');
    labels.forEach(label => {
        label.classList.remove('active');
    });

    // Добавляем класс active к метке, связанной с выбранной радиокнопкой
    let correspondingLabel = document.querySelector(`label[for="${selectedRadio.id}"]`);
    if (correspondingLabel) {
        correspondingLabel.classList.add('active');
    }
}

// Инициализация при загрузке страницы (если есть выбранная радио-кнопка)
window.onload = function() {
    let checkedRadio = document.querySelector('input[name="genre"]:checked');
    if (checkedRadio) {
        updateActive(checkedRadio); // Вызываем обновление для уже выбранной радиокнопки
    }
}