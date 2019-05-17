"use strict";

window.onload = function () {
    addEventListenerForViewMentorsButton();
    addEventListenerForNotificationOfMentor();
};

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