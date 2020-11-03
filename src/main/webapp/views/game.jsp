<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link
  rel="stylesheet" type="text/css"
  href="${pageContext.request.contextPath}/static/css/app.css"
/>

</head>
<body>
 
  <button id="send">Send msg</button>
  
  <input type="text" id="nickanme" placeholder="Есіміңді еңгіз">
  <button id ="searchGame" class="btn btn-search-game">Ойнау</button>
  
  
  <div id="game-container">

        <div class="game-cards-container">
            <div class="settings">
                <div class="timer">10</div>
                <div class="game-timer">
                    5:00
                </div>
            </div>
            
	          <div class="opponent-cards">
	            <ul class="cards">
	                <li>
	                    <div class="card" id="center-card">
	                        <img src="" alt="">
	                        <p>Some card 2 1</p>
	                    </div>
	                </li>
	                <li>
	                    <div class="card" id="center-card">
	                        <img src="" alt="">
	                        <p>Some card 2 2 </p>
	                    </div>
	                </li>
	                <li>
	                    <div class="card" id="center-card">
	                        <img src="" alt="">
	                        <p>Some card 2 3</p>
	                    </div>
	                </li>
	            </ul>
	
	        </div>
        </div>
		<div class="center-cards-container">
           <ul class="timeline-cards">
               <li id="center-card" data-id="1" data-position="0">
                   <div class="card" >
                       <img src="" alt="">
                       <p>Some card 0</p>
                   </div>
               </li>
           </ul>
		</div>

		<div class="game-cards-container">
            <div class="settings">
                <div class="timer">10</div>
                <div class="game-timer">
                    5:00
                </div>
            </div>
            <div class="own-cards">
            <ul class="cards">
                <li data-id="122">
                    <div class="card card1" >
                        <img src="test.jpg" alt="">
                        <p>Some card 1 1</p>
                    </div>
                </li>
                <li data-id="123"  >
                    <div class="card card2">
                        <img src="" alt="">
                        <p>Some card 1 2 </p>
                    </div>
                </li>
                <li data-id="124">
                    <div class="card card3">
                        <img src="" alt="">
                        <p>Some card 1 3</p>
                    </div>
                </li>
            </ul>
        </div>
        </div>
    </div>
    
  <div class="loader-container" id="loader">
  
 	</div>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.5.1.min.js"></script> 
 	<script src="https://cdn.jsdelivr.net/npm/sortablejs@1.12.0/dist/sortable.umd.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app.js"></script> 
</body>
</html>