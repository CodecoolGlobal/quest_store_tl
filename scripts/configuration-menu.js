function openForm() {
    document.getElementById("action-form-create-mentor").style.visibility = "visible";
}

function closeForm() {
    document.getElementById("action-form-create-mentor").style.visibility = "hidden";
}

window.onload = closeForm;

var button = document.getElementById("create-mentor-button");
console.log(button);
button.addEventListener("click", openForm);