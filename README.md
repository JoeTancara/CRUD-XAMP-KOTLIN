📱 Aplicación Android con MySQL - XAMP

Esta es una aplicación Android que interactúa con una base de datos MySQL utilizando Volley para las solicitudes de red. Permite a los usuarios realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre registros de usuarios.

🚀 Características

Insertar Nuevos Registros: Añade usuarios fácilmente con un formulario intuitivo.

Actualizar Registros Existentes: Modifica datos de usuarios seleccionados.

Eliminar Registros: Borra usuarios de forma rápida.

Ver Todos los Registros: Visualiza datos de usuarios en una tabla interactiva.

🛠️ Requisitos

Android Studio: Entorno de desarrollo.

Servidor MySQL: Base de datos para almacenar los registros.

Servidor PHP: Backend para gestionar las solicitudes de la app.

⚙️ Configuración

Clona el repositorio:

git clone https://github.com/JoeTancara/CRUD-XAMP-KOTLIN.git

Abre el proyecto en Android Studio.

Configura el servidor MySQL y PHP:

Asegúrate de que tu servidor MySQL esté funcionando y accesible.

Sube los scripts PHP (insertar.php, editar.php, borrar.php, registros.php, registro.php) a tu servidor PHP.

Actualiza las URLs en el archivo MainActivity.kt:

En "tuservidor" ahi va la ip de tu servidor local o remoto

val url = "http://tuservidor/ANDROID_MYSQL/insertar.php"

Ejecuta la aplicación:

Compila y ejecuta en un dispositivo Android o emulador.

📋 Uso

Insertar Usuario: Rellena los datos del usuario y pulsa el botón "INSERTAR".

Actualizar Usuario: Selecciona un registro, edita los datos y pulsa "EDITAR".

Eliminar Usuario: Pulsa "BORRAR" junto a un registro para eliminarlo.

Ver Usuarios: Los registros se muestran en una tabla en la pantalla principal.

📸 Capturas de Pantalla

Pantalla Principal

[![ASD.jpg](https://i.postimg.cc/rFKnGfdz/ASD.jpg)](https://postimg.cc/WhcwT6wP)



¡Derechos reservados JoeDev! 🎉
