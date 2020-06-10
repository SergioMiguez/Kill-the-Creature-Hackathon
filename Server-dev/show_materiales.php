<?php
include 'connexion.php';
// Seleccionar pedidos de un hospital
$query = "SELECT * FROM objetos";

$resultado = $conexion -> query($query);

while($fila = mysqli_fetch_assoc($resultado)){
    $product[] = array_map('utf8_encode',$fila);
}

echo json_encode($product);
$conexion->close();
?>