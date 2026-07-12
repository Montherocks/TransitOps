const loginForm =
document.getElementById("loginForm");

loginForm.addEventListener(
"submit",
function(e){

e.preventDefault();

const email =
document.getElementById("email").value;

const password =
document.getElementById("password").value;

const role =
document.getElementById("role").value;

const error =
document.getElementById("errorMessage");

if(!role){

error.textContent =
"Please select a role.";

return;

}

error.textContent = "";

const userData = {

email,
role

};

localStorage.setItem(
"user",
JSON.stringify(userData)
);

window.location.href =
"dashboard.html";

}
);