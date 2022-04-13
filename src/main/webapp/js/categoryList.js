$(document).ready(function () {
    $('.deactivate-btn').click(function() {
        const button = $(this);
        const activeTd = button.parent().siblings('.activeTd');
        const ctId = button.data("ctId");

        $.ajax({
            url: `/api/categories/deactivate/${ctId}`,
            type: "PATCH",
            success: function() {
                activeTd.text("INATIVA");
                button.fadeOut({
                    duration: 750
                });
            },
            error: function() {
                alert("Não foi possível desativar a categoria, tente novamente!");
            }
        });
    })
});