"use strict";

import { addEventListenerForTakeActionContainer } from './configuration-menu.js';

window.onload = function () {
    handleViewButtonsRedirection();
    enableEventListenersForTakeActionContainer();
};

function redirectToCodecoolersPage() {
    window.location.href="/codecoolers";
}

function redirectToQuestsPage() {
    window.location.href="/quests";
}

function redirectToArtifactsPage() {
    window.location.href="/artifacts";
}

function redirectToTeamsPage() {
    window.location.href="/teams";
}

function addEventListenerForViewCodecoolersButton() {
    let viewCodecoolersButton = document.getElementById("view-codecoolers-button");
    viewCodecoolersButton.addEventListener("click", redirectToCodecoolersPage);
}

function addEventListenerForViewQuestsButton() {
    let viewQuestsButton = document.getElementById("view-quests-button");
    viewQuestsButton.addEventListener("click", redirectToQuestsPage);
}

function addEventListenerForViewArtifactsButton() {
    let viewArtifactsButton = document.getElementById("view-artifacts-button");
    viewArtifactsButton.addEventListener("click", redirectToArtifactsPage);
}

function addEventListenerForViewTeamsButton() {
    let viewArtifactsButton = document.getElementById("view-teams-button");
    viewArtifactsButton.addEventListener("click", redirectToTeamsPage);
}

function handleViewButtonsRedirection() {
    addEventListenerForViewCodecoolersButton();
    addEventListenerForViewQuestsButton();
    addEventListenerForViewArtifactsButton();
}

function enableEventListenersForTakeActionContainer() {
    addEventListenerForTakeActionContainer("create-student-button", "create-student-form");
    addEventListenerForTakeActionContainer("create-quest-button", "create-quest-form");
    addEventListenerForTakeActionContainer("create-artifact-button", "create-artifact-form");
    addEventListenerForTakeActionContainer("create-team-button", "create-team-form");
    addEventListenerForTakeActionContainer("review-quests-button", "review-quests-form");
    addEventListenerForTakeActionContainer("review-artifacts-button", "review-artifacts-form");
}
