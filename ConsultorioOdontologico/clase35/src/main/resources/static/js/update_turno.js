window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la pelicula
    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function (event) {
        let turnoId = document.querySelector('#turno_id').value;

        //creamos un JSON que tendrá los datos de la película
        //a diferencia de una pelicula nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const formData = {
            id: document.querySelector('#turno_id').value,
            paciente: {
                        id: document.querySelector('#paciente_id').value,
                        },
            odontologo: {
                        id: document.querySelector('#odontologo_id').value,
                        },
            fecha: document.querySelector('#fecha').value,

        };

        //invocamos utilizando la función fetch la API turnos con el método PUT que modificará
        //el turno que enviaremos en formato JSON
        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })

    //Es la funcion que se invoca cuando se hace click sobre el id de un turno del listado
    //se encarga de llenar el formulario con los datos del turno
    //que se desea modificar
    function findBy(id) {
          const url = '/turnos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let turno = data;

              document.querySelector('#turno_id').value= turno.id;
              document.querySelector('#paciente_id').value= turno.paciente.id;
              document.querySelector('#odontologo_id').value= turno.odontologo.id;
              document.querySelector('#fecha').value= turno.fecha;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_turno_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }