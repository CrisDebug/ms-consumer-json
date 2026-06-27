package com.alertas.ms_alertas.consumer;

import com.alertas.ms_alertas.config.RabbitConfig;
import com.alertas.ms_alertas.dto.AlertaMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class AlertaJsonConsumer {

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void recibirAlerta(AlertaMessage mensaje) {

        System.out.println("📩 Evento JSON recibido: " + mensaje.getMensaje());

        guardarComoJson(mensaje);
    }

    private void guardarComoJson(AlertaMessage mensaje) {

        try {
            String carpeta = "alertas-json";
            File dir = new File(carpeta);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = carpeta + "/alerta_" + System.currentTimeMillis() + ".json";

            FileWriter writer = new FileWriter(fileName);

            writer.write("{\n");
            writer.write("  \"pacienteId\": " + mensaje.getPacienteId() + ",\n");
            writer.write("  \"mensaje\": \"" + mensaje.getMensaje() + "\",\n");
            writer.write("  \"estado\": \"ARCHIVADO\",\n");
            writer.write("  \"timestamp\": \"" + LocalDateTime.now() + "\"\n");
            writer.write("}\n");

            writer.close();

            System.out.println("📁 JSON generado: " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}