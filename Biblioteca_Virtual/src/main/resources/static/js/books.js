$(document).ready(function() {
    //----------------inicializar datatable----------------
    if ($('#booksTable tbody tr').length > 0) {
        $('#booksTable').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json"
            }
        });
    }


    //-------------------delete book-------------------
    document.querySelectorAll('.btn-delete').forEach(btn => {
        btn.addEventListener('click', function(e) {
            e.preventDefault();
            const href = this.getAttribute('href');

            Swal.fire({
                title: 'Are you sure?',
                text: "This book will be deleted!",
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


    //----- Loan Book ----->>>>>
     document.querySelectorAll('.btn-loan').forEach(btn => {
            if (btn.textContent.trim() === 'Loan') {
                btn.addEventListener('click', function (e) {
                    e.preventDefault();
                    const href = this.getAttribute('href');

                    Swal.fire({
                        title: 'Do you want to loan this book?',
                        icon: 'question',
                        showCancelButton: true,
                        confirmButtonText: 'Yes, loan it!',
                        cancelButtonText: 'Cancel'
                    }).then((result) => {
                        if (result.isConfirmed) {
                         window.location.href = href;
                        }
                    });
                });
            }
        });

        //----- Return Book ----->>>>>
        document.querySelectorAll('.return').forEach(form => {
            const btn = form.querySelector('button');
            if (btn && btn.textContent.trim() === 'Return') {
                form.addEventListener('submit', function (e) {
                    e.preventDefault();

                    Swal.fire({
                        title: 'Do you want to return this book?',
                        icon: 'question',
                        showCancelButton: true,
                        confirmButtonText: 'Yes, return it!',
                        cancelButtonText: 'Cancel'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            form.submit();
                        }
                    });
                });
            }
        });


     });