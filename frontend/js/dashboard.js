const dashboardData = {

    stats: [

        {
            title: "Active Vehicles",
            value: "--"
        },

        {
            title: "Available Vehicles",
            value: "--"
        },

        {
            title: "Maintenance",
            value: "--"
        },

        {
            title: "Active Trips",
            value: "--"
        },

        {
            title: "Pending Trips",
            value: "--"
        },

        {
            title: "Drivers On Duty",
            value: "--"
        },

        {
            title: "Fleet Utilization",
            value: "--"
        }

    ],

    trips: [],

    vehicleStatus: []

};

const statsContainer =
document.getElementById("statsContainer");

dashboardData.stats.forEach(card => {

statsContainer.innerHTML += `

<div class="stat-card">

    <small>${card.title}</small>

    <h2>${card.value}</h2>

</div>

`;

});

function renderTrips(trips){

const table =
document.getElementById("tripTable");

table.innerHTML = "";

trips.forEach(trip=>{

table.innerHTML += `

<tr>

<td>${trip.id}</td>
<td>${trip.vehicle}</td>
<td>${trip.driver}</td>
<td>${trip.status}</td>

</tr>

`;

});

}

function renderVehicleStatus(data){

const container =
document.getElementById("vehicleStatus");

container.innerHTML="";

data.forEach(item=>{

container.innerHTML += `

<div class="status-bar">

    <p>${item.label}</p>

    <div class="progress">

        <div
            style="
            width:${item.value}%;
            background:${item.color};
            "
        ></div>

    </div>

</div>

`;

});

}

renderTrips([]);
renderVehicleStatus([]);

const user =
JSON.parse(localStorage.getItem("user"));

if(user){

document.getElementById(
"profileRole"
).textContent = user.role;

}