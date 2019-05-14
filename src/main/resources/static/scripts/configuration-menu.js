"use strict";

window.onload = function () {
    EnableEventListenerToggleHideShowForm();
};

function EnableEventListenerToggleHideShowForm() {
    let takeActionContainers = document.getElementsByClassName("take-action-container");
    let actionForms = document.getElementsByClassName("action-form-container");

    for (let i = 0; i < takeActionContainers.length; i++) {
        for (let j = 0; j < actionForms.length; j++) {
            takeActionContainers[i].addEventListener("click", function () {
                toggleHideShowActionForm(actionForms[i]);
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
        openActionForm(actionForm);
    } else {
        closeActionForm(actionForm);
    }
}