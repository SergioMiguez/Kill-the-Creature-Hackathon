<?php
include 'connexion.php';
$usuario = $_POST['usuario'];
$id = $_POST['id'];
$fecha = $_POST['fecha_envio'];

$TRUE = 1;
$FALSE = 0;

$query_getId = "SELECT id FROM proveedores where usuario = '$usuario'";
$resultado= $conexion ->query($query_getId);
if(mysqli_num_rows($resultado)){
    $id_proveedor = $resultado ->fetch_assoc()["id"];

}
else{
    throw new Exception("Fatal error");
}
$query_enviado = "UPDATE pedidos_conectados SET enviado = '$TRUE', fecha_envio = '$fecha' WHERE id = '$id' AND id_proveedor = '$id_proveedor' AND recibido = 0 AND enviado = 0";

$resultado2= $conexion->query($query_enviado);

if (mysqli_affected_rows($conexion) != 0){
    $result = Array();
    $result["success"] = true;
    echo json_encode($result);

}
else {
    $result = Array();
    $result["success"] = false;
    echo json_encode($result);

}

?>