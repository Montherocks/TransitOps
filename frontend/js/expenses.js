const expenses = [];

function renderExpenses(data){

const table =
document.getElementById("expenseTable");

table.innerHTML="";

if(data.length===0){

table.innerHTML=`
<tr>
<td colspan="5"
style="text-align:center;padding:40px;">
No Expense Records
</td>
</tr>
`;

return;
}

data.forEach(expense=>{

table.innerHTML += `

<tr>

<td>${expense.date}</td>

<td>${expense.vehicle}</td>

<td>${expense.category}</td>

<td>${expense.amount}</td>

<td>

<button class="action-btn">
View
</button>

</td>

</tr>

`;

});

}

renderExpenses(expenses);