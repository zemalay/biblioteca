$(function() {
	
	$('#alunoForm').hide();

	$('#linkAluno').click(function() {
		$('#funcionarioForm').hide();
		$('#alunoForm').show();
	});

	$('#linkFuncionario').click(function() {
		$('#alunoForm').hide();
		$('#funcionarioForm').show();
	});

	$("#menu-toggle").click(function(e) {
		e.preventDefault();
		$("#wrapper").toggleClass("toggled");
	});

	$(".dropdown-toggle").dropdown();

});