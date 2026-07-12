const trips = [];

function renderTrips(data){

const board =
document.getElementById("tripBoard");

board.innerHTML="";

if(data.length===0){

board.innerHTML=`
<div class="trip-card">
No Active Trips
</div>
`;

return;

}

data.forEach(trip=>{

board.innerHTML += `

<div class="trip-card">

<h4>${trip.id}</h4>

<p>
${trip.origin}
→
${trip.destination}
</p>

</div>

`;

});

}

renderTrips(trips);