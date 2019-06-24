"use strict";

import { addEventListenerForTakeActionContainer } from './configuration-menu.js';

window.onload = function () {
    addEventListenerForViewMentorsButton();
    enableEventListenersForTakeActionContainer();
    addEventListenerForNotificationOfMentor();
};

function showMentorNotification() {
    let notificationContainer = document.getElementById("creepy-guy-notification-message");
    notificationContainer.style.visibility = "visible";
    notificationContainer.innerText = "Mentor has been recruited.";
}

function redirectToMentorsPage() {
    window.location.href = "/mentors";
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

function enableEventListenersForTakeActionContainer() {
    addEventListenerForTakeActionContainer("create-mentor-button", "create-mentor-form");
    addEventListenerForTakeActionContainer("create-room-button", "create-room-form");
    addEventListenerForTakeActionContainer("edit-levels-button", "edit-levels-form");
}

