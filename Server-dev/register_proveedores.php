<?php

include 'connexion.php';
$nombre =$_POST['nombre'];
$usuario =$_POST['usuario'];
$descripcion =$_POST['descripcion'];
$email = $_POST['email'];
$password =$_POST['password'];
$direccion =$_POST['direccion'];
$telefono =$_POST['telefono'];
//$usuario = "test";
//$nombre = "nombre edit";
//$direccion = "aaaaaa";
//$email = "bxcvbx";
//$telefono = "erwtert";
//$descripcion = "hola";
//$password ="test";

$consulta = "INSERT INTO proveedores (nombre, usuario, password, direccion, telefono, email, descripcion) VALUES ('".$nombre."', '".$usuario."', '".$password."', '".$direccion."', '".$telefono."', '".$email."','".$descripcion."')";

$res = mysqli_query($conexion,$consulta);

if ($res){
    $result = Array();
    $result["success"] = true;
    echo json_encode($result);

}
else {
    $result = Array();
    $result["success"] = false;
    echo json_encode($result);

}
$conexion -> close();
?>


