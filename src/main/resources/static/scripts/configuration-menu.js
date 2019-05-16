"use strict";

window.onload = function () {
    EnableEventListenerToggleHideShowForm();
    addEventListenerForViewMentorsButton();
    addEventListenerForNotificationOfMentor();
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

function showMentorNotification() {
    let notificationContainer = document.getElementById("creepy-guy-notification-message");
    notificationContainer.style.visibility = "visible";
    notificationContainer.innerText = "Mentor has been recruited.";
}

function redirectToMentorsPage() {
    window.location.href="/mentors";
}

function addEventListenerForViewMentorsButton() {
    let viewMentorsButton = document.getElementById("view-mentors-button");
    viewMentorsButton.addEventListener("click", redirectToMentorsPage);
}

function addEventListenerForNotificationOfMentor() {
    let submitButton = document.getElementById("create-mentor-form");
    submitButton.addEventListener("submit", handleMentorNotification);
}

function handleMentorNotification() {
    showMentorNotification();
}