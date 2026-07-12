const vehicles = [];

function renderVehicles(data){

const table =
document.getElementById("vehicleTable");

table.innerHTML = "";

if(data.length === 0){

table.innerHTML = `

<tr>

<td colspan="6"
style="
text-align:center;
padding:40px;
">

No Vehicles Found

</td>

</tr>

`;

return;

}

data.forEach(vehicle=>{

table.innerHTML += `

<tr>

<td>${vehicle.id}</td>

<td>${vehicle.type}</td>

<td>

<span class="status ${vehicle.statusClass}">

${vehicle.status}

</span>

</td>

<td>${vehicle.capacity}</td>

<td>${vehicle.driver}</td>

<td>

<button class="action-btn">

View

</button>

</td>

</tr>

`;

});

}

renderVehicles(vehicles);