function deactivateCategory(event, element) {
    event.preventDefault();

    const id = element.id.replace(/\D/g, '');

    const xhr = new XMLHttpRequest();
    xhr.open("DELETE", `http://localhost:8080/categorias/desativar/${id}`);

    xhr.addEventListener("load", function () {
        if (xhr.status === 204) {
            const td = element.closest("tr").getElementsByClassName("active")[0];
            td.textContent = "INATIVA";

            element.remove();
        }
    });

    xhr.send();
}

