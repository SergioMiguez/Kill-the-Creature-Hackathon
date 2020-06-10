<?php
include 'connexion.php';

$usuario = $_POST['usuario'];
//$usuario = "test";

// Encontrar ID de proveedor a partir de nombre de usuario
$queryProveedor = "SELECT id FROM proveedores WHERE usuario = '$usuario'";

$resultadoProveedor = $conexion ->query($queryProveedor);

if(mysqli_num_rows($resultadoProveedor)){
    $id_proveedor = $resultadoProveedor ->fetch_assoc()["id"];
}
else{
    throw new Exception("FATAL ERROR: Hospital not in our database");
}

// Seleccionar pedidos conectados de un proveddor
$query = "SELECT * FROM pedidos_conectados WHERE id_proveedor = '$id_proveedor'";

$resultadoFinal = $conexion -> query($query);

while($fila = mysqli_fetch_assoc($resultadoFinal)){

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