package com.alertas.ms_alertas.dto;

public class AlertaMessage {

    private Long pacienteId;
    private String mensaje;

    public AlertaMessage() {
    }

    public AlertaMessage(Long pacienteId, String mensaje) {
        this.pacienteId = pacienteId;
        this.mensaje = mensaje;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}