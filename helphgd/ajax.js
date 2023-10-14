var ticketData = $('#listTickets').DataTable({
	"lengthChange": false,
	"processing":true,
	"serverSide":true,
	"order":[],
	"ajax":{
		url:"process.php",
		type:"POST",
		data:{action:'listTicket'},
		dataType:"json"
	},
	"columnDefs":[
		{
			"targets":[0, 6, 7, 8, 9],
			"orderable":false,
		},
	],
	"pageLength": 10
});