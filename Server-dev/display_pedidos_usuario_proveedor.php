<?php
include 'connexion.php';
// Seleccionar pedidos de un hospital
$query = "SELECT * FROM pedidos";

$resultado = $conexion -> query($query);

while($fila = mysqli_fetch_assoc($resultado)){

    $fila['nombre_objeto'] = helper($fila['id_objeto']);
    $product[] = array_map('utf8_encode',$fila);
}

echo json_encode($product);
$conexion->close();

// Encontrar el nombre de un objeto en base a su ID
function helper($id){
	include 'connexion.php';

    $queryObjetos = "SELECT nombre FROM objetos WHERE id = '$id'";

    $resultadoObjetos = $conexion ->query($queryObjetos);

    if(mysqli_num_rows($resultadoObjetos)){
    $nombre_objeto = $resultadoObjetos ->fetch_assoc()['nombre'];
    }

    else{
        throw new Exception("Object not in our database");
}
$conexion->close();
return $nombre_objeto;
}
?>