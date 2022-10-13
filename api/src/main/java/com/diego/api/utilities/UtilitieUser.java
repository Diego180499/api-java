/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diego.api.utilities;

import com.diego.api.dto.user.response.send_message.ResponseSendMessageDTO;
import org.springframework.http.HttpStatus;

/**
 *
 * @author HP
 */
public class UtilitieUser {

    public static ResponseSendMessageDTO verifyCodeSaveUser(int codigo) {
        ResponseSendMessageDTO responseSend = new ResponseSendMessageDTO();

        switch (codigo) {
            case 200:
                responseSend.setMensaje("Guardado Correctamente");
                responseSend.setCodigo(HttpStatus.OK.value());
                break;
            case 400:
                responseSend.setMensaje("Todos los campos son obligatorios");
                responseSend.setCodigo(HttpStatus.BAD_REQUEST.value());
                break;
            default:
                break;
        }

        return responseSend;

    }

    public static ResponseSendMessageDTO verifyCodeSendMessage(int code) {

        ResponseSendMessageDTO responseSend = new ResponseSendMessageDTO();

        switch (code) {
            case 200:
                responseSend.setMensaje("Mensaje enviado correctamente");
                responseSend.setCodigo(HttpStatus.OK.value());
                break;
            case 400:
                responseSend.setMensaje("Error en el número de celular");
                responseSend.setCodigo(HttpStatus.BAD_REQUEST.value());
                break;
            case 401:
                responseSend.setMensaje("Error en el servidor, el token no ha sido actualizado");
                responseSend.setCodigo(HttpStatus.UNAUTHORIZED.value());
                break;
            default:
                break;
        }

        return responseSend;

    }

}
