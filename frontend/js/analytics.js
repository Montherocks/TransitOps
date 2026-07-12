const utilizationCtx =
document.getElementById("utilizationChart");

new Chart(utilizationCtx,{

type:"bar",

data:{
labels:[],
datasets:[
{
label:"Fleet Utilization",
data:[]
}
]
}

});

const expenseCtx =
document.getElementById("expenseChart");

new Chart(expenseCtx,{

type:"line",

data:{
labels:[],
datasets:[
{
label:"Expenses",
data:[]
}
]
}

});