"use strict";

window.onload = function () {
    EnableEventListenerToOpenAForm();
};

function EnableEventListenerToOpenAForm() {
    let takeActionContainers = document.getElementsByClassName("take-action-container");
    let actionForms = document.getElementsByClassName("action-form-container");

    for (let i = 0; i < takeActionContainers.length; i++) {
        for (let i = 0; i < actionForms.length; i++) {
            takeActionContainers[i].addEventListener("click", function () {
                openActionForm(actionForms[i]);
            });
        }
    }
}

function closeActionForm(actionForm) {
    actionForm.style.display = "none";
}

function openActionForm(actionForm) {
    actionForm.style.display = "block";
}

function toggleHideShowActionForm(actionForm) {
    if (actionForm.style.display === "none") {
        actionForm.style.display = "block";
    } else {
        actionForm.style.display = "none";
    }
}