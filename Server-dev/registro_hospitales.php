<?php

include 'connexion.php';
$nombre =$_POST['nombre'];
$usuario =$_POST['usuario'];
$password =$_POST['password'];
$direccion =$_POST['direccion'];
$telefono =$_POST['telefono'];
$email = $_POST['email'];

//$nombre = "test11";
//$usuario ="test11";
//$password ="test11";
//$direccion ="test11";
//$telefono ="test11";
//$email = "test11";

$consulta = "INSERT INTO hospitales(nombre, usuario, password, direccion, telefono, email) VALUES ('".$nombre."', '".$usuario."', '".$password."', '".$direccion."', '".$telefono."', '".$email."')";

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


