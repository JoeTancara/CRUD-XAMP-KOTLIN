<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once 'conexion.php';
    $id=$_POST['id'];
    $nombre=$_POST['nombre'];
    $email=$_POST['email'];
    $telefono=$_POST['telefono'];
    $pass=$_POST['pass'];
    $query="UPDATE usuarios SET nombre='".$nombre."', email='".$email."', telefono='".$telefono."', pass='".$pass."' WHERE id='".$id."'";
    $result=$mysql->query($query);
    if($mysql->affected_rows>0 && $result==true){
        echo "Datos actualizados correctamente";
    }else{ 
        echo "Error al actualizar los datos";
    }
    $mysql->close();
}