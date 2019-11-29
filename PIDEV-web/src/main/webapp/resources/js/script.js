$(document).ready(function() {
	$.fn.datepicker.dates['fr'] = ({
		days : [ "Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi",
				"Samedi" ],
		daysShort : [ "Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam" ],
		daysMin : [ "Di", "Lu", "Ma", "Me", "Je", "Ve", "Sa" ],
		months : [ "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet",
				"Août", "Septembre", "Octobre", "Novembre", "Decembre" ],
		monthsShort : [ "Jan", "Fév", "Mar", "Avr", "Mai", "Jun", "Jul", "Aoû",
				"Sep", "Oct", "Nov", "Dec" ],
		today : "Ajourd'hui",
		clear : "Annuler",
	});

	$('.datepicker').datepicker({
		autoclose : true,
		format : 'dd/mm/yyyy',
		language : 'fr',
		todayHighlight : true,
		weekStart : 1,
		startView:2,
		startDate: new Date(),
		clearBtn: true
	});

	$('.datepicker-icon').click(function() {
		console.log("sss")
		$(this).closest('.input-group').find('.datepicker').focus();
	});
	
	
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

