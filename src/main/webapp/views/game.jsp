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
            <div class="cards">

            </div>
        </div>
        <div class="timeline-container">

        </div>

        <div class="game-cards-container">
            <div class="settings">
                <div class="timer">10</div>
                <div class="game-timer">
                    5:00
                </div>
            </div>
            <div class="cards">
				
				<div class="card">
					<div class="card-img-container">
						<img src="https://e-history.kz/upload/iblock/a78/a78456c811efa07c0bcf4d05636f0487.jpg">
					</div>
					
					<div class="card-desc">
						Керей-хан — первый казахский хан, один из основателей Казахского ханства и его правитель 
					</div>
				</div>
				
            </div>
        </div>
    </div>
    
  <div class="loader-container" id="loader">
  
 	</div>
 <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app.js"></script> 
</body>
</html>