//const url = "https://minticloud.uis.edu.co/c3s56formador/api/clientes"
const url = "http://localhost:8080/CRUDrockola/approckola/usuarios";
const contenedor = document.querySelector('tbody')
let resultados = ''


const modalusuarios = new bootstrap.Modal(document.getElementById('modalusuario'))
const formUsuarios = document.querySelector('form')
const nombreUsuario = document.getElementById('nombres')
const apellidosUsuario = document.getElementById('apellidos')
const emailUsuario = document.getElementById('email')
const sexoUsuario = document.getElementById('sexo')
const PasswordUsuario = document.getElementById('password')
const cod_nacionalidad = document.getElementById('cod_nacionalidad')
const cod_usuario = document.getElementById('cod_usuario')

let opcion = ''

btnCrear.addEventListener('click', () => {
    nombreUsuario.value = ''
    apellidosUsuario.value = ''
    emailUsuario.value = ''
    sexoUsuario.value = ''
    PasswordUsuario.value = ''
    cod_nacionalidad.value = ''
    cod_usuario.value = ''
    cod_usuario.disabled = false
    modalusuarios.show()
    opcion = 'crear'
})


const ajax = (options) => {
    let { url, method, success, error, data } = options;
    const xhr = new XMLHttpRequest();

    xhr.addEventListener("readystatechange", (e) => {
        if (xhr.readyState !== 4) return;

        if (xhr.status >= 200 && xhr.status < 300) {
            let json = JSON.parse(xhr.responseText);
            success(json);
        } else {
            let message = xhr.statusText || "Ocurrió un error";
            error(`Error ${xhr.status}: ${message}`);
        }
    });

    xhr.open(method || "GET", url);
    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
    xhr.send(JSON.stringify(data));
};



const getAll = () => {
    ajax({
        url: url,
        method: "GET",
        success: (res) => {
            console.log(res);

            res.forEach((usuarios) => {
                resultados += `<tr>
                        <td width="5%">${usuarios.cod_usuario}</td>
                        <td width="20%">${usuarios.nombres}</td>
                        <td width="20%">${usuarios.apellidos}</td>
                        <td width="15%">${usuarios.email}</td>
                        <td width="5%">${usuarios.sexo}</td>
                        <td width="10%">${usuarios.password}</td>
                        <td width="5%">${usuarios.cod_nacionalidad}</td>
                        <td class="text-center" width="20%"><a class="btnEditar btn btn-success">Editar</a><a class="btnBorrar btn btn-danger">Borrar</a></td>
                    </tr>`
            });

            contenedor.innerHTML = resultados
        },
        error: (err) => {
            console.log(err);
            $table.insertAdjacentHTML("afterend", `<p><b>${err}</b></p>`);
        },
    });
};
document.addEventListener("DOMContentLoaded", getAll);
document.addEventListener("click", (e) => {

    if (e.target.matches(".btnBorrar")) {
        const fila = e.target.parentNode.parentNode
        const id = fila.firstElementChild.innerHTML
        console.log(id)
        alertify.confirm(`¿Estás seguro de eliminar al usuario ${id}?`,
            function () {
                ajax({
                    url: url + "/" + id,
                    method: "DELETE",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    success: (res) => location.reload(),
                    error: (err) => alert(err),
                });
                alertify.success('Registro eliminado')
            },
            function () {
                alertify.error('Accion cancelada')
            });



    }
    if (e.target.matches(".btnEditar")) {
        const fila = e.target.parentNode.parentNode
        cod_usuario.value = fila.children[0].innerHTML
        nombreUsuario.value = fila.children[1].innerHTML
        apellidosUsuario.value = fila.children[2].innerHTML
        emailUsuario.value = fila.children[3].innerHTML
        sexoUsuario.value = fila.children[4].innerHTML
        PasswordUsuario.value = fila.children[5].innerHTML
        cod_nacionalidad.value = fila.children[6].innerHTML
        
        cod_usuario.disabled = true
        opcion = 'editar'
        modalusuarios.show()
    }
})

formUsuarios.addEventListener('submit', (e) => {
    e.preventDefault()
    let metodo = "POST"
    if (opcion == 'editar') {
        metodo = "PUT"

    }
    ajax({
        url: url,
        method: metodo,
        headers: {
            'Content-Type': 'application/json'
        },
        success: (res) => location.reload(),
        error: (err) =>
            $form.insertAdjacentHTML("afterend", `<p><b>${err}</b></p>`),
        data: {
   
            "nombres": nombreUsuario.value,
            "apellidos": apellidosUsuario.value,
            "email": emailUsuario.value,
            "sexo": sexoUsuario.value,
            "password": PasswordUsuario.value,
            "cod_nacionalidad": cod_nacionalidad.value,
            "cod_usuario": cod_usuario.value
        },
    });
    alertify.success('Registro Guardado')
    modalusuarios.hide()
})