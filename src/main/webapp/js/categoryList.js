function addtoev() {
    const bns = document.querySelectorAll(".delete-icn");

    for (let i = 0; i < bns.length; i++) {
        bns[i].addEventListener("click", deleteCategory);
    }
}

function deleteCategory(event) {
    event.preventDefault();

    const id = this.id.replace(/\D/g, '');

    const xhr = new XMLHttpRequest();
    xhr.open("DELETE", `http://localhost:8080/categorias/deletar/${id}`);

    xhr.addEventListener("load", function () {
        if (xhr.status === 204) {
            const row = document.querySelector(`#category${id}`);
            const column = row.getElementsByClassName("active");

            column[0].textContent = "INATIVA";
        }
    });

    xhr.send();
}

window.addEventListener("load",function() {
    addtoev();
});



