"use strict";

// грязно домогаемся до кнопки в навбаре
let searchButton = document.querySelector('#toggleSearchButton');

// получаем блок в котором находится поиск
let searchBlock = document.querySelector('.searchBlock');

// функция проверит есть ли у элемента класс сырчХыдден
function showAndHideSearch(){
    // если класс СырчХыддэн есть, то убираем его, появляется поиск, а на кнопке пишем "скрыть поиск"
    if(searchBlock.classList.contains('search-hidden')){
        searchBlock.classList.remove('search-hidden');
        searchButton.textContent = 'Скрыть поиск';
    //     Иначе, т.е. если класса такого нет, то мы его назначаем, а на кнопке пишем "Показать поиск"
    } else {
        searchBlock.classList.add('search-hidden');
        searchButton.textContent = 'Показать поиск';
    }
}

searchButton.addEventListener('click', showAndHideSearch);
