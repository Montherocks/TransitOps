const drivers = [];

function renderDrivers(data){

const table =
document.getElementById("driverTable");

table.innerHTML = "";

if(data.length===0){

table.innerHTML = `
<tr>
<td colspan="6" style="text-align:center;padding:40px;">
No Drivers Found
</td>
</tr>
`;

return;

}

data.forEach(driver=>{

table.innerHTML += `

<tr>

<td>${driver.id}</td>

<td>${driver.name}</td>

<td>${driver.expiry}</td>

<td>

<span class="${driver.complianceClass}">
${driver.compliance}
</span>

</td>

<td>${driver.vehicle}</td>

<td>

<button class="action-btn">
View
</button>

</td>

</tr>

`;

});

}

renderDrivers(drivers);