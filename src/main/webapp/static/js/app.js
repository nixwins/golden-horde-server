
const sendMsgBtn = document.getElementById("send");
const msgInp = document.getElementById("msg");
const searchGameBtn = document.getElementById("searchGame");
const loaderDiv = document.getElementById("loader");

	let ws = new WebSocket("ws://localhost:8080/golden-horde-server/server");

searchGameBtn.addEventListener("click", function(){
	
	searchGameBtn.disabled = true;
	loaderDiv.style.display  = "block";
	
	ws.onopen = function(e){
		console.log("Conecting...");
	};

	
	ws.onmessage = function(e){
			
		console.log(e.data);
		
		if(e.data == 'yes'){
			console.log("game found");
		
			loaderDiv.style.display  = "none";
		}
		
		
	}
	
	ws.send("search");
	

		
}, false);










sendMsgBtn.addEventListener("click",  function(e){
	ws.send(msgInp.value);
});

