$(document).ready(function () {
    $('.deactivate-btn').click(function() {
        const button = $(this);
        const activeTd = button.parent().siblings('.activeTd');
        const sbId = button.data("sbId");

        $.ajax({
            url: `/api/subcategories/deactivate/${sbId}`,
            type: 'PATCH',
            success: function() {
                activeTd.text("INATIVA");
                button.fadeOut({
                    duration: 750
                });
            },
            error: function() {
                alert("Não foi possível desativar a subcategoria, tente novamente!");
            }
        });
    })
});