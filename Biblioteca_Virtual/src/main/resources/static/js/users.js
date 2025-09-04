$(document).ready(function() {
    //----------------inicializar datatable----------------
    if ($('#usersTable tbody tr').length > 0) {
        $('#usersTable').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json"
            }
        });
    }


    //-------------------delete book-------------------
    $('#usersTable').on('click', '.btn-delete', function(e) {
            e.preventDefault();
            const href = $(this).attr('href');

            Swal.fire({
                title: 'Are you sure?',
                text: "This user will be deleted!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#d33',
                cancelButtonColor: '#3085d6',
                confirmButtonText: 'Yes, delete it!',
                cancelButtonText: 'Cancel'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = href;
                }
            });

    });

});

