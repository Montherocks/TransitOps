const maintenanceRecords = [];

function renderMaintenance(data){

const table =
document.getElementById("maintenanceTable");

table.innerHTML = "";

if(data.length === 0){

table.innerHTML = `
<tr>
<td colspan="6"
style="text-align:center;padding:40px;">
No Maintenance Records
</td>
</tr>
`;

return;
}

data.forEach(record=>{

table.innerHTML += `

<tr>

<td>${record.vehicle}</td>

<td>${record.type}</td>

<td>${record.dueDate}</td>

<td>

<span class="${record.statusClass}">
${record.status}
</span>

</td>

<td>${record.cost}</td>

<td>

<button class="action-btn">
View
</button>

</td>

</tr>

`;

});

}

renderMaintenance(maintenanceRecords);