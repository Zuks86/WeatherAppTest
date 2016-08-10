$(window).load(onLoad);
$(window).resize(onResize);

function onLoad() {
	animate();
}

function onResize() {
	// code to call when page resizes
}

var elementsEnterTL;
function animate() {
	elementsEnterTL = new TimelineLite();
	elementsEnterTL.from("h1", 1, {
		opacity: 0, 
		y:"-50px",
		ease:Back.easeOut
	}).from("img", 1, {
		opacity: 0, 
		y:"-50",
		ease:Back.easeOut.config(4)
	}).from(".button:nth-child(1)", 1.2, {
		opacity: 0,
		x: -30,
		ease:Elastic.easeOut.config(1, 0.4)
	}, "buttonEntry")
	.from(".button:nth-child(2)", 1.2, {
		opacity: 0,
		x: 30,
		ease:Elastic.easeOut.config(1, 0.4)
	}, "buttonEntry");
	
	elementsEnterTL.timeScale(1.7);
	elementsEnterTL.pause();
    
    sleep(100,function(){ // brief pause to make sure the done is visible before animation begins
    	elementsEnterTL.restart();
    });
}

function sleep(millis, callback) {
    setTimeout(function(){ callback(); }, millis);
}