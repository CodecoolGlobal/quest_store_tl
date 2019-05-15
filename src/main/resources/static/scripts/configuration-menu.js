"use strict";

window.onload = function () {
    EnableEventListenerToggleHideShowForm();
    hideNotificationMessageContainer();
    addEventListenerForViewMentorsButton();
};

function EnableEventListenerToggleHideShowForm() {
    let takeActionButtons = document.getElementsByClassName("action-button");
    let actionForms = document.getElementsByClassName("action-form-container");

    for (let i = 0; i < takeActionButtons.length; i++) {
        for (let j = 0; j < actionForms.length; j++) {
            takeActionButtons[i].addEventListener("click", function () {
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

function hideNotificationMessageContainer() {
    document.getElementById("creepy-guy-notification-message").style.visibility = "hidden";
}

function redirectToMentorsPage() {
    window.location.href="http://localhost:8000/mentors"
}

function addEventListenerForViewMentorsButton() {
    let viewMentorsButton = document.getElementById("view-mentors-button");
    viewMentorsButton.addEventListener("click", redirectToMentorsPage);
}