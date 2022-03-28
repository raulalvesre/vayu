function deactivateCategory(event, element) {
    event.preventDefault();

    const id = element.id.replace(/\D/g, '');

    const xhr = new XMLHttpRequest();
    xhr.open("DELETE", `http://localhost:8080/categorias/deletar/${id}`);

    xhr.addEventListener("load", function () {
        if (xhr.status === 204) {
            const td = document.querySelector(`#active${id}`);
            td.textContent = "INATIVA";

            element.remove();
        }
    });

    xhr.send();
}

