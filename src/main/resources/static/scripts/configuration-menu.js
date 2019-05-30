"use strict";

function closeActionForm(actionForm) {
    actionForm.style.display = "none";
}

function openActionForm(actionForm) {
    actionForm.style.display = "block";
}

function toggleHideShowActionForm(actionForm) {
    if (actionForm.style.display === "block") {
        closeActionForm(actionForm);
    } else {
        openActionForm(actionForm);
    }
}

export function addEventListenerForTakeActionContainer(createButton, createForm) {
    let createObjectButton = document.getElementById(createButton);
    let createObjectForm = document.getElementById(createForm);
    createObjectButton.addEventListener("click", function () {
        toggleHideShowActionForm(createObjectForm);
    });
}