<?php
include 'connexion.php';

$id_pedido =intval($_POST['id_pedido']);
$fecha_recibido = $_POST['fecha_recibido'];
$usuario = $_POST['usuario'];

$FALSE = 0;
$queryHospital = "SELECT id FROM hospitales WHERE usuario = '$usuario'";

$resultadoHospital = $conexion ->query($queryHospital);

if(mysqli_num_rows($resultadoHospital)){
    $id_hospital = $resultadoHospital ->fetch_assoc()["id"];
    //echo $id_hospital;
}
else{
    throw new Exception("FATAL ERROR: Hospital not in our database");
}
$True =1;
$queryRecibido = "UPDATE pedidos_conectados SET fecha_recibido ='$fecha_recibido', recibido = '$True' WHERE id ='$id_pedido' AND enviado = '$True' AND id_hospital = '$id_hospital' AND recibido ='$FALSE' ";
$resultado = $conexion->query($queryRecibido);
if (mysqli_affected_rows($conexion)==1){
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