<?php 
if($_SERVER['REQUEST_METHOD']=='POST'){
    require_once 'conexion.php';
    $nombre = $_POST['nombre'];
    $email = $_POST['email'];
    $telefono = $_POST['telefono'];
    $pass = $_POST['pass'];
    
    $stmt = $mysql->prepare("INSERT INTO usuarios (nombre, email, telefono, pass) VALUES (?, ?, ?, ?)");
    $stmt->bind_param("ssss", $nombre, $email, $telefono, $pass);
    
    if($stmt->execute()){
        echo "Registro exitoso";
    }else{
        echo "Error al registrar";
    }
    
    $stmt->close();
    $mysql->close();
}
?>