<?php
include 'connexion.php';
$usuario = $_POST['usuario'];
//$usuario = "test";

$query = "SELECT * from hospitales WHERE usuario = '$usuario'";
$resultado = $conexion->query($query);
while($fila = mysqli_fetch_assoc($resultado)){
    $product[] = array_map('utf8_encode',$fila);
}

echo json_encode($product);
$conexion -> close();

?>