<?php
include 'connexion.php';
$usuario =$_REQUEST['usuario'];
$password=$_REQUEST['password'];
$resultado = $conexion ->query("SELECT * FROM usuarios WHERE usuario = $usuario AND password =$password")
if ($resultado ->rowcount() > 0 ){
    return TRUE;
}
else{
    return FALSE;
}

$conexion ->close()


?>