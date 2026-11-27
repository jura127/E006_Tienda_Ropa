
document.querySelectorAll('select[data-autoenviar]').forEach(function (sel) {
	sel.addEventListener('change', function () {
		document.getElementById('frmFiltros').submit();
	});
});


document.querySelectorAll('.prenda .miniatura').forEach(function (mini) {
	mini.addEventListener('click', function () {
		var prenda = mini.closest('.prenda');
		prenda.querySelector('.prendaImg').src = mini.dataset.src;
		prenda.querySelectorAll('.miniatura').forEach(function (m) { m.classList.remove('activa'); });
		mini.classList.add('activa');
	});
});