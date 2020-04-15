<?php
include 'connexion.php';
$usuario = $_POST['usuario'];
$id_pedido =intval($_POST['id_pedido']);

//$usuario = "test";
//$id_pedido = 46;

$queryHospital = "SELECT id FROM hospitales WHERE usuario = '$usuario'";

$resultadoHospital = $conexion ->query($queryHospital);

if(mysqli_num_rows($resultadoHospital)){
    $id_hospital = $resultadoHospital ->fetch_assoc()["id"];
    //echo $id_hospital;
}
else{
    throw new Exception("FATAL ERROR: Hospital not in our database");
}


$query = "SELECT * from pedidos_pendientes WHERE id ='$id_pedido' AND id_hospital ='$id_hospital'";
$resultado = $conexion -> query($query);

while($fila = mysqli_fetch_assoc($resultado)){

    $fila['nombre_objeto'] = helper($fila['id_objeto']);
    $product[] = array_map('utf8_encode',$fila);
}

echo json_encode($product);

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
$conexion -> close();
return $nombre_objeto;
}
$conexion -> close();


?>