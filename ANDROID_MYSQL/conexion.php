<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "android_mysql";
// Create connection
$mysql = new mysqli($servername, $username, $password, $dbname);
if ($mysql->connect_error) {
    die("Erro de Conexion: " . $mysql->connect_error);
}else{
    // echo "Connexion exitosa";
}

?>