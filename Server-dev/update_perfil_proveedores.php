<?php
include 'connexion.php';
//el usuario se recibe a través de LoginActivity.

$usuario = $_POST['usuario'];
$nombre = $_POST['nombre'];
$direccion = $_POST['direccion'];
$email =$_POST['email'];
$telefono = $_POST['telefono'];
$descripcion =$_POST['descripcion'];


//$usuario = "test";
//$nombre = "nombre edit";
//$direccion = "a$d$s$f$a$s$dfasd";
//$email = "bxcvbx";
//$telefono = "erwtert";
//descripcion = "hola";

$query = "UPDATE proveedores SET nombre ='$nombre',direccion ='$direccion',email ='$email',telefono ='$telefono', descripcion = '$descripcion'  WHERE usuario = '$usuario'";

$resultado = $conexion -> query($query);
if($resultado){
$result = Array();
    $result["success"] = true;
    echo json_encode($result);

}
else{
    $result = Array();
    $result["success"] = false;
    echo json_encode($result);
}
$conexion ->close();
?>