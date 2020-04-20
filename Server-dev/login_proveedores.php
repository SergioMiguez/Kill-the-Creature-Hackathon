<?php
include 'connexion.php';

$usuario = $_POST['usuario'];
$password = $_POST['password'];
$query = ("SELECT * FROM proveedores WHERE usuario = '$usuario' AND password ='$password'");

$resultado = mysqli_query($conexion,$query);

if($fila = $resultado ->fetch_assoc()){
    echo json_encode($fila,JSON_UNESCAPED_UNICODE);
}

$conexion ->close();
?>