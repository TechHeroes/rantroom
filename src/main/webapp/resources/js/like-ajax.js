$(document).on('click', '.lu', function(){
	console.log('***clicked', $(this));
	$(".lu").prop("disabled", true);  
	var ele = $(this);
	var mode = ele.data('mode');
	var rId = ele.data('rid');
	console.log("Rant ID: ", rId);
	post_like(ele, mode, rId);
});

function post_like(e, mode, rId) {
    var ele=$(e);
	var parent = ele.parent();
	var url = "/likes/"+1+"/" + (mode == "1" ? "unlike" :"like");
	console.log("URL : ",url);
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
				parent.html('<i data-mode="-1" data-rid= "'+rId+'" class="lu far fa-thumbs-up" style="color:#337ab7;font-size: 1.5em" aria-hidden="true"></i>');				
			}
			else{
				parent.html('<i data-mode="1" data-rid= "'+rId+'" class="lu fas fa-thumbs-up" style="color:#337ab7;font-size: 1.5em" aria-hidden="true"></i>');
			}	
            
			$(".lu").prop("disabled", false);           
            
        },
        error: function (e) {
			alert("Uh Oh! You need to login first!");
            console.log("ERROR : ", e);
            $(".lu").prop("disabled", false);
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