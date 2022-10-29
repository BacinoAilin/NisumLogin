Prueba Técnica NISUM 
Autor: Bacino Ailin


Tecnologías Implementadas
- JDK 11
- Spring Boot
- Git
- Maven
- JPA
- Hibernate
- H2
- Pruebas Unitarias con Junit
- Token JWT

Todos los mensajes siguen el formato:
{"mensaje": "mensaje de error"}

- Si el correo existe en la base de datos, retorna un error "El correo ya está registrado".
- El correo sigue una expresión regular para validar que su formato sea el correcto.
 La clave sigue una expresión regular para validar que formato sea el correcto.

Diagrama de la solución

- Ejecución con maven y Java
- clonar el proyecto $ https://github.com/BacinoAilin/NisumLogin
- Ir al directorio del proyecto $ cd nisum/
- Generar jar del proyecto
- $ mvn clean install
- Correr proyecto con Maven $ mvn spring-boot:run
