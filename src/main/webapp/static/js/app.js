
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



$(document).ready(function(){

    const ownCardContainerEl = document.querySelector(".own-cards .cards");
    const timeLineContainerEl = document.querySelector(".timeline-cards");

    new Sortable(ownCardContainerEl, {
        group: {
            name: 'own-cards',
        },
        animation: 150
    });

    const sortableTimeLine = new Sortable(timeLineContainerEl, {
        group: {
            name: 'timelinecards',
            put:'own-cards',
           
            animation: 150
        },
        sort:false,
        swap:true,
        onSort: function (evt) {

            const prevEl = $(evt.item).prev();
            const nextEl = $(evt.item).next();
            // console.log(prevEl)
            addPosition(evt.item, prevEl, nextEl);

            const cardID  = evt.item.getAttribute("data-id");
            const cardPosition  = evt.item.getAttribute("data-id");
            ws.send(JSON.stringify({card_id:cardID}));

            
            
        },
       
    });

    const addPosition = function(item, prevEl, nextEl){
    
      let position;
      const nextLi = $(nextEl)[0];
      const prevLi = $(prevEl)[0];

      if(prevEl.length > 0  && nextEl.length > 0){

         let  prevPos = Number($(prevEl).attr("data-position"));
         let nextPos = Number($(nextEl).attr("data-position"));
         position = nextPos;
         item.setAttribute("data-position", position);
         updateNextPosition(position, nextEl);

      }

      if(nextEl != undefined && nextEl.length > 0){
        
            position = Number($(nextLi).attr("data-position")) - 1;
      }

      if(prevEl.length > 0 && prevEl != undefined){
          position = Number($(prevLi).attr("data-position")) + 1;
      }
   
        
      item.setAttribute("data-position", position);

    }

    const updateNextPosition = function(position, nextEl){
        
        const parentNextEl  = $(nextEl).parent();
        const timelineCardsContainer  = $(parentNextEl)[0];

        console.log(timelineCardsContainer)

        $(timelineCardsContainer).children().each(function(idx, val){

            let currPos = $(this).attr("data-position");
            console.log(typeof currPos)
            console.log("currPos " + currPos + " pos " + position)

            if(position > 0 && currPos >= position){
              
                    $(this).attr("data-position", Number.parseInt(currPos)+1)
            }else if(currPos != 0 && position < 0){
                $(this).attr("data-position", Number.parseInt(currPos)-1)
            }
           
         
        })
    }
});


