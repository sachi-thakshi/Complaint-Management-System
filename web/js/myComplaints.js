document.querySelectorAll('.delete-btn').forEach(button => {
    button.addEventListener('click', function () {
        const form = this.closest('form');

        Swal.fire({
            title: 'Are you sure?',
            text: "This complaint will be permanently deleted!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#3085d6',
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'Cancel'
        }).then((result) => {
            if (result.isConfirmed) {
                form.submit();
            }
        });
    });
});