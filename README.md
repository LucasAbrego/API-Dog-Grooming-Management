# API-Dog-Grooming-Management
La aplicación es un sistema de gestión para una peluquería canina, desarrollada completamente en el backend utilizando tecnologías como Spring Boot, Hibernate, MySQL, y validaciones con Jakarta Validation API. Permite manejar citas de grooming para mascotas, clientes, y administrar turnos con servicios como baño y corte de pelo.

Enpoints:

Data Controller

POST /data/reset: Reinicia y carga datos de prueba en la base de datos.

DELETE /data/deleteAll: Elimina todos los datos de la base de datos.

Client Controller
POST /clients/save: Crea un nuevo cliente.
GET /clients: Obtiene todos los clientes activos.
GET /clients/all: Obtiene todos los clientes (activos e inactivos).
GET /clients/{dni}: Obtiene un cliente por su DNI.
GET /clients/{dni}/billing: Obtiene el historial de pagos de un cliente.
PUT /clients/edit/{dni}: Actualiza los datos de un cliente existente.
PUT /clients/deactivate/{dni}: Desactiva un cliente.
PUT /clients/activate/{dni}: Activa un cliente.
DELETE /clients/delete/{dni}: Elimina un cliente.

Pet Controller
Endpoints:
POST /pets/save: Crea una nueva mascota.
GET /pets: Obtiene todas las mascotas activas.
GET /pets/all: Obtiene todas las mascotas (activas e inactivas).
GET /pets/client/{dni}: Obtiene las mascotas de un cliente por su DNI.
GET /pets/{petId}: Obtiene una mascota por su ID.
PUT /pets/edit/{petId}: Actualiza los datos de una mascota existente.
PUT /pets/deactivate/{petId}: Desactiva una mascota.
PUT /pets/activate/{petId}: Activa una mascota.
DELETE /pets/delete/{petId}: Elimina una mascota.

Turn Controller
Endpoints:
POST /turns/save: Crea un nuevo turno.
GET /turns/all: Obtiene todos los turnos.
GET /turns/{turnId}: Obtiene un turno por su ID.
GET /turns/pet/{petId}: Obtiene los turnos de una mascota por su ID.
GET /turns/client/{dni}: Obtiene los turnos de un cliente por su DNI.
GET /turns/findByDataRange: Obtiene los turnos dentro de un rango de fechas.
PUT /turns/edit/{turnId}: Actualiza los datos de un turno existente.
PUT /turns/cancel/{turnId}: Cancela un turno.
PUT /turns/pay/{turnId}: Marca un turno como pagado.
DELETE /turns/delete/{turnId}: Elimina un turno.
