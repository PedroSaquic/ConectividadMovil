**Prompts clave usados**
#1-al presionar el boton "Crear" muestra failed to connect to /10.0.2.2(port 8080) from /10.0.2.16(port58508)after 10000ms

#2-entonces la ip de android o mi aplicacion es 10.0.2.2 y esta intentando conectar al puerto 8080(puerto que esta escuchando) 
tendria que estar esperando una conexion pero no lo hace porque no se ha iniciado la escucha?

#3-aparte de lo que se hace en android es necesario inicializar aparte el puerto 8080 ya que los @getmapping, @postmapping etc, deben ser para 
obtener productos referentes a ala aplicacion en adroidStudio?

#4-como puedo iniciar el back end para abrir el puerto 8080

**Capturas**
--crear(./img/crear.png)
--lista(./img/lista.png)
--actualizar(./img/actualizar.png)
--busqueda(./img/busqueda.png)

**Errores Encontrados**
#1-al presionar el boton "Crear" muestra failed to connect to /10.0.2.2(port 8080) from /10.0.2.16(port58508)after 10000ms 
--solucion: activar el puerto 8080 para ejecutar el programa

#2-Whitelabel Error Page This application has no explicit mapping for /error, so you are seeing this as a fallback. Mon Oct 06 22:06:42 CST 2025 There was an unexpected error (type=Not Found, status=404).
--solucion: generar el backend controlador poder conectar la aplicacion con el puerto 8080 y los endpoints(en java)

#3-error al usar JpaRepository
--solucion: pom.xml (Maven), agrega dentro de <dependencies>

#4-Whitelabel Error Page This application has no explicit mapping for /error, so you are seeing this as a fallback. al momento de visualizar en swagger-ui
--solucion: reiniciar netbeans, build and clean, ejecutar nuevamente en "http://localhost:8080/swagger-ui/index.html"

**MVVM (Model–View–ViewModel) y Retrofit**
--Es una arquitectura para organizar el código y separar responsabilidades:
--Retrofit es la librería que permite comunicarse con tu API REST fácilmente.

**Reflexion**
--uso de chatgpt como copiloto para compartir prompt definidos y asi poder llevar el orden requerido por Retrofit y MVVM
asi como para la resolucion de dudas sobre que funcionamiento tiene cada clase, objeto, interfaz y un uso correcto en 
android studio, al finalizar con la estructura tambien fue escencial realizar las preguntas correctas para poder crear la
conexion hacia spring boot y tom cat lo que se obtuvo fue una conexion en memoria para que se pueda utilizar REST y los 
endpoints que se crearon en androidStudio.
