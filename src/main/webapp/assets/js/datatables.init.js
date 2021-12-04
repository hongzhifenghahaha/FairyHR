$(document).ready(function () {
    $('#datatable').DataTable();
    $('#datatable2').DataTable();
    var table = $('#datatable-buttons').DataTable({lengthChange: false, buttons: ['copy', 'excel', 'pdf', 'colvis']});
    table.buttons().container().appendTo('#datatable-buttons_wrapper .col-md-6:eq(0)');

});