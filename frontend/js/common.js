const sidebarItems = [
  "Dashboard",
  "Fleet",
  "Drivers",
  "Trips",
  "Maintenance",
  "Fuel & Expenses",
  "Analytics",
  "Settings"
];

const user =
JSON.parse(localStorage.getItem("user"));

const profileRole =
document.getElementById("profileRole");

if(user && profileRole){

    profileRole.textContent =
    user.role;

}

const logoutBtn =
document.getElementById("logoutBtn");

if(logoutBtn){

    logoutBtn.addEventListener("click",()=>{

        localStorage.removeItem("user");

        window.location.href =
        "index.html";

    });

}

