<?php
$hostname='localhost';
$database ='matalbicho';
$password ='';

$conexion =new mysqli($hostname,$username,$password, $database);
if($conexion -> connect_errno){
    echo "Error en la conexión"
}


?>