package DiegoE.proyecto;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador {

    Juego juego = new Juego();

    /*P E T I C I ON E S         G E T*/
    @GetMapping("/pruebaGet")
    public String obtenerMensaje(@RequestParam(value = "mensaje", defaultValue = "HOLA") String variable) {
        return "Mensaje: " + variable;
    }

    /**
     * Obtener un nivel del arbol
     *
     * @param nivel
     * @return
     */
    @GetMapping("Game/get-level")
    public String obtenerNivel(@RequestParam(value = "level", defaultValue = "0") int nivel) {
        int nivelConsultado = nivel;
        juego.buscarNivel(nivelConsultado);
        String response = juego.nodosObtenidos();

        return response;

    }

    @GetMapping("Game/avltree")
    public String obtenerOrden(@RequestParam(value = "transversal", defaultValue = "inOrder") String orden) {
        String response = "";

        if (orden.equals("inOrder")) {
            response = juego.enOrden();
        }
        if (orden.equals("preOrder")) {
            response = juego.preOrden();
        }

        if (orden.equals("postOrder")) {
            response = juego.postOrden();
        }

        return response;
    }

    @GetMapping("Game/status-avltree")
    public String obtenerEstadoArbol(@RequestParam(value = "status", defaultValue = "") String orden) {

        String ruta = "{\n"
                + "\"path\":\"" + juego.generarGrafica() + "\"";

        return ruta;

    }

    /*P E T I C I ON E S         P O S T*/
    /**
     * Agregar una carta al Arbol
     *
     * @param json
     * @return
     */
    @PostMapping("Game/add")
    public ResponseEntity<String> insertar(@RequestBody String json) {
        
        int estado = juego.insertar(json);
        if(estado == 406){
            return new ResponseEntity<>("La carta a insertar esta duplicada", HttpStatus.NOT_ACCEPTABLE);
        }
        if(estado == 400){
            return new ResponseEntity<>("Hay una Carta que es mayor a 13", HttpStatus.BAD_REQUEST);
        }
        juego.actualizarNivel();
        return new ResponseEntity<>("inserción exitosa", HttpStatus.OK);
    }

    /**
     * iniciamos el juego
     *
     * @param json
     * @return
     */
    @PostMapping("Game/start")
    public ResponseEntity<String> iniciarJuego(@RequestBody String json) {
        int estado = juego.iniciarJuego(json);
        
         if(estado == 406){
            return new ResponseEntity<>("La carta a insertar esta duplicada", HttpStatus.NOT_ACCEPTABLE);
        }
        if(estado == 400){
            return new ResponseEntity<>("Hay una Carta que es mayor a 13", HttpStatus.BAD_REQUEST);
        }
        
        juego.actualizarNivel();
        return new ResponseEntity<>("inserción exitosa", HttpStatus.OK);
    }

    @DeleteMapping("Game/delete")
    public ResponseEntity<String> eliminar(@RequestBody String json) {
        int estado = juego.eliminar(json);
        
        if(estado == 400){
            return new ResponseEntity<>("Hay una Carta que es mayor a 13 ó menor a 1", HttpStatus.BAD_REQUEST);
        }

        if (estado == 404) {
            return new ResponseEntity<>("UNA DE LAS CARTAS NO SE ENCUENTRA EN EL ARBOL", HttpStatus.NOT_FOUND);
        }

        if (estado == 406) {
            return new ResponseEntity<>("LOS VALORES NO SUMAN 13", HttpStatus.NOT_ACCEPTABLE);
        }

        if (estado == 409) {
            return new ResponseEntity<>("UNA DE LAS CARTAS TIENE HIJOS", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("ELIMINACION EXITOSA", HttpStatus.OK);
    }

}
