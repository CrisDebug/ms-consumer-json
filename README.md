# Microservicio Consumer (B)

## 📌 Descripción

El Microservicio Consumer es responsable de recibir y procesar mensajes en formato JSON enviados por el Microservicio Producer. Su objetivo es validar la comunicación entre microservicios mediante el consumo de datos estructurados.

---

## ⚙️ Responsabilidad principal

- Recibir mensajes JSON desde el Producer.
- Validar la estructura del mensaje.
- Procesar la información recibida.
- Registrar o almacenar el resultado del procesamiento.

---

## 📥 Formato del mensaje (JSON)

Ejemplo de payload esperado:

```json
{
  "id": 1,
  "evento": "CREAR_ORDEN",
  "timestamp": "2026-06-26T12:00:00Z",
  "data": {
    "cliente": "Juan Perez",
    "producto": "Laptop",
    "cantidad": 1
  }
}

Ejemplo de request:

POST /consumer/event
Content-Type: application/json

Ejemplo de response:

{
  "status": "OK",
  "message": "Evento procesado correctamente",
  "receivedId": 1
}


🔄 Flujo de procesamiento
El Producer envía un JSON al endpoint del Consumer.
El Consumer recibe y valida la estructura.
Se procesa el contenido del evento.
Se genera una respuesta de confirmación.
🧪 Objetivo
Validar la comunicación entre microservicios.
Asegurar recepción correcta de mensajes JSON.
Demostrar procesamiento básico de eventos.
🚀 Posibles mejoras
Validación con esquemas (JSON Schema).
Procesamiento asíncrono con colas (RabbitMQ / Kafka).
Persistencia en base de datos.
Manejo de reintentos y errores