$(document).on('click', '.lu', function(){
	console.log('***clicked', $(this));
	var ele = $(this);
	var mode = ele.data('mode');
	var rId = ele.data('rid');
	console.log("Rant ID: ", rId);
	//console.log("MODE: ", mode);
	post_like(ele, mode, rId);
});

function post_like(e, mode, rId) {
    
	//var rantId = document.getElementById('rId').value;
	//console.log("Rant ID : ",rantId);
	//stop submit the form, we will post it manually.
    //event.preventDefault();
    //$(e).prop("disabled", true);
    var ele=$(e);
	var parent = ele.parent();
	var url = "/likes/"+1+"/" + (mode == "1" ? "unlike" :"like");
	console.log("URL : ",url);
	console.log("parent value: ",parent);
    $.ajax({
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        url: url,
//        data: JSON.stringify(search),
		headers: {"Access-Control-Allow-Origin" : "*"},
        dataType: 'html',
        cache: false,
        timeout: 600000,
        success: function () {
            console.log("SUCCESS : ");
			if(mode == "1"){				
				parent.html('<i data-mode="-1" data-rid= 1 class="lu far fa-thumbs-up" style="color:#337ab7;font-size: 1.5em" aria-hidden="true"></i>');
				console.log("inside mode = 1");
				//ele.removeClass('fas fa-thumbs-up');
				//ele.addClass('far fa-thumbs-up');
				//mode = 0;
			}
			else{
				parent.html('<i data-mode="1" data-rid= 1 class="lu fas fa-thumbs-up" style="color:#337ab7;font-size: 1.5em" aria-hidden="true"></i>');
				console.log("inside mode = -1");
				//ele.removeClass('far fa-thumbs-up');
				//ele.addClass('fas fa-thumbs-up');
				//mode = 1;
			}	
            
			//$("#btn-search").prop("disabled", false);            
            
        },
        error: function (e) {
			alert("Something went wrong!");
            console.log("ERROR : ", e);
            //$("#unlike-button").prop("disabled", false);
        }
    });
}    

//			var element = document.getElementById("like-button");
//			element.classList.remove("far");
//			element.classList.add("fas");
//			element.classList.toggle("visible");
//element.classList.toggle("far,fa-thumbs-up");
    /*function fire_ajax_unlike(e) {
		var rantId = document.getElementById('rId').value; // $('#rId').val()
		console.log("Rant ID : ",rantId);
		//stop submit the form, we will post it manually.
		event.preventDefault();
		
		var ele=$(e)
		console.log("Ele value: ',ele);
        $("#like-button").prop("disabled", true);
		
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            url: "/likes/unlike/"+rantId,
//            data: JSON.stringify(search),
			headers: {"Access-Control-Allow-Origin" : "*"},
            dataType: 'html',
            cache: false,
            timeout: 600000,
            success: function () {

                console.log("SUCCESS : ");				
				ele.removeClass('fas fa-thumbs-up')
				ele.addClass('far fa-thumbs-up')
				
				//var element = document.getElementById("unlike-button");
				//element.classList.remove("fas");
				//element.classList.add("far");
				//element.classList.toggle("visible");
				//element.classList.toggle("fas,fa-thumbs-up");
//                $("#btn-search").prop("disabled", false);

            },
            error: function (e) {
				alert("You need to login first!");
                console.log("ERROR : ", e);
                $("#like-button").prop("disabled", false);

            }
            //console.log("args: ", arguments);
        });
}*/