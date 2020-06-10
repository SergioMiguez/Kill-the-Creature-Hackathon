<?php
include 'connexion.php';

$user = $_POST['user'];
$id = intval($_POST['id']);

//$user = "test";
//$id = 45;

$True = 1;
$False = 0;

$queryproveedor = "SELECT id FROM proveedores WHERE usuario = '$user'";
$resultadoProveedor = $conexion ->query($queryproveedor);
if(mysqli_num_rows($resultadoProveedor)){
    $id_proveedor = $resultadoProveedor ->fetch_assoc()["id"];
  
}
else{
    throw new Exception("FATAL ERROR: Producer not in our database");
}

$queryUpdate = "UPDATE pedidos_conectados SET completado ='$True' WHERE completado = '$False' AND enviado = '$False'  AND recibido = '$False'  AND id = '$id' AND id_proveedor = '$id_proveedor'";
$resultado = $conexion->query($queryUpdate);
if (mysqli_affected_rows($conexion)!=0){
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