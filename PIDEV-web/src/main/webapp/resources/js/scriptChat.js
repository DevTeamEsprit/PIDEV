$(document).ready(function() {
	document.getElementById("message").focus();
	let scrolled = false;
	
	function uploadScroll() {
		console.log(scrolled)
		if (!scrolled) {
			
			var element = document.getElementById("direct-chat-messages");
			element.scrollTop = element.scrollHeight;
		}
	}

	function getMessages() {
		$formChat = $("#formChat").serialize();
		$.ajax({
			async : false,
			type : "POST",
			url : "../ChatServlet",
			data : $formChat,
			success : function(msg) {
				$('#direct-chat-messages').html(msg);
			}
		});

		uploadScroll();
	}

	setInterval(function() {
		getMessages();
	}, 1000);

	function sendMessage() {
		$formChat = $("#formChat").serialize();
		$.ajax({
			async : false,
			type : "POST",
			url : "../EnvoiMessageServlet",
			data : $formChat,
			success : function(msg) {
				$('#direct-chat-messages').html(msg);
				$("#message").val("");
				var element = document.getElementById("direct-chat-messages");
				element.scrollTop = element.scrollHeight;
			}
		});
	}

	$("#direct-chat-messages").on('scroll', function() {
		scrolled = true;
	});

	getMessages();

	$("#message").keydown(function(e) {
		if (e.keyCode == 13) {
			if ($("#message").val() != '') {
				sendMessage();
				return false;
			}
		}
	});

	$("#btn-send-message").click(function() {
		if ($("#message").val() != '') {
			sendMessage();
		}
	});
	$("#direct-chat-messages").height($('body').height()-300);

	/*
	function asideHeight() {
		if ($(".content-wrapper").height() > $(window).height()) {
			$(".control-sidebar").height($(window).height());
		} else {
			$(".control-sidebar").height($(window).height() - 57);
		}
	}
	*/
	//asideHeight();
	$(".control-sidebar").height($(window).height() - 57);
	window.onscroll = function () {
		if ($('.control-sidebar').is(':visible')) {
			$(".control-sidebar").css({ position: "fixed" });
			if (document.body.scrollTop > 57 || document.documentElement.scrollTop > 57) {
				$(".control-sidebar").css({ top: "0px" });
			} else {
				$(".control-sidebar").css({ position: "", top: "57px" });
			}
		} else {
			$(".control-sidebar").css({ position: "" });
		}

	};
});
