<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
    require_once 'conexion.php';
    $id=$_POST['id'];
    $query="DELETE FROM usuarios WHERE id='".$id."'";
    $resultado=$mysql->query($query);
    if($mysql->affected_rows>0){
        if($resultado==true){
            echo "Registro eliminado";
        }
    }else{
        echo "Error al eliminar";
    }
    $mysql->close();
}