"use strict";

// Скрываем системные требования, если при создании игры была выбрана не платформа windows
// А еще, в input у системных требований занесется Null
let platformSelect = document.getElementById("platformSelect");
let systemRequirementsDiv = document.getElementById("systemRequirementsDiv");
let systemRequirementsInput = document.getElementById("systemRequir");
function itsConsoleHideRequirements(){
    if(platformSelect.value != 'Windows'){
        systemRequirementsDiv.style.display = 'none';
        systemRequirementsInput.textContent = null;
    } else {
        systemRequirementsDiv.style.display = 'block';
    }
}
platformSelect.addEventListener('change', itsConsoleHideRequirements);
itsConsoleHideRequirements();