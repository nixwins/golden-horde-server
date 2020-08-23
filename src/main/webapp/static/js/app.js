
const sendMsgBtn = document.getElementById("send");
const msgInp = document.getElementById("msg");
const searchGameBtn = document.getElementById("searchGame");


	let ws = new WebSocket("ws://localhost:8080/golden-horde-server/server");

searchGameBtn.addEventListener("click", function(){
	
	
	ws.onopen = function(e){
		console.log("Conecting...");
	};

	
	ws.onmessage = function(e){
			
		console.log(e.data);
		
		
	}
	
	ws.send(msgInp.value);
	

		
}, false)








sendMsgBtn.addEventListener("click",  function(e){
	
});

