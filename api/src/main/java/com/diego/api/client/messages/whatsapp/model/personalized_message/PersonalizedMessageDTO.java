/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.api.client.messages.whatsapp.model.personalized_message;

/**
 *
 * @author HP
 */
public class PersonalizedMessageDTO {

    private String numero;
    private String mensaje;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {

        return "{\n"
                + "    \"messaging_product\": \"whatsapp\",\n"
                + "    \"recipient_type\": \"individual\",\n"
                + "    \"to\": \"" + numero + "\",\n"
                + "    \"type\": \"text\",\n"
                + "    \"text\": { \n"
                + "        \"preview_url\": false,\n"
                + "        \"body\": \"" + mensaje + "\"\n"
                + "    }\n"
                + "}";
    }

}
