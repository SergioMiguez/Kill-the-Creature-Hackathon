<?php
$hostname='localhost';
$database ='matalbicho';
$username = 'root';
$password ='admin';

$conexion =new mysqli($hostname,$username,$password, $database);
if($conexion -> connect_errno){
    echo "Error en la conexión";
}


?>