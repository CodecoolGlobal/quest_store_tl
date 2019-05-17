"use strict";

window.onload = function () {
    handleViewButtonsRedirection();
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