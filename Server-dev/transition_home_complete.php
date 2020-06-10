<?php
include 'connexion.php';

$usuario = $_POST['usuario'];
$id_proveedor =intval($_POST['id_proveedor']);
$id=intval($_POST['id']);

//$usuario = "test";
//$id_proveedor = 2;
//$id=47;

$queryHospital = "SELECT id FROM hospitales WHERE usuario = '$usuario'";

$resultadoHospital = $conexion ->query($queryHospital);

if(mysqli_num_rows($resultadoHospital)){
    $id_hospital = intval($resultadoHospital ->fetch_assoc()["id"]);
    //echo $id_hospital;
}
else{
    throw new Exception("FATAL ERROR: Hospital not in our database");
}


// CANTIDAD

$queryCantidad = "SELECT cantidad FROM pedidos_pendientes WHERE id = '$id' AND id_proveedor = '$id_proveedor' AND id_hospital = '$id_hospital'";

$resultadoCantidad = $conexion ->query($queryCantidad);

if(mysqli_num_rows($resultadoCantidad)){
    $cantidad = intval($resultadoCantidad ->fetch_assoc()["cantidad"]);
    //echo $id_hospital;
}
else{
    throw new Exception("FATAL ERROR: Hospital not in our database");
}

// ID OBJETO

$queryObjeto = "SELECT id_objeto FROM pedidos_pendientes WHERE id = '$id' AND id_proveedor = '$id_proveedor' AND id_hospital = '$id_hospital'";

$resultadoObjeto = $conexion ->query($queryObjeto);

if(mysqli_num_rows($resultadoObjeto)){
    $id_objeto = intval($resultadoObjeto ->fetch_assoc()["id_objeto"]);
    //echo $id_hospital;
}
else{
    throw new Exception("FATAL ERROR: Hospital not in our database");
}

// FECHA

$queryFecha = "SELECT fecha FROM pedidos_pendientes WHERE id = '$id' AND id_proveedor = '$id_proveedor' AND id_hospital = '$id_hospital'";

$resultadoFecha = $conexion ->query($queryFecha);

if(mysqli_num_rows($resultadoFecha)){
    $fecha = $resultadoFecha ->fetch_assoc()["fecha"];
    //echo $id_hospital;
}
else{
    throw new Exception("FATAL ERROR: Hospital not in our database");
}

// DIRECCION
$queryDireccion = "SELECT direccion_envio FROM pedidos_pendientes WHERE id = '$id' AND id_proveedor = '$id_proveedor' AND id_hospital = '$id_hospital'";

$resultadoDireccion = $conexion ->query($queryDireccion);

if(mysqli_num_rows($resultadoDireccion)){
    $direccion_envio = $resultadoDireccion ->fetch_assoc()["direccion_envio"];
    //echo $id_hospital;
}
else{
    throw new Exception("FATAL ERROR: Hospital not in our database");
}

$query1 = "INSERT INTO pedidos_conectados (id, id_objeto, cantidad, id_proveedor, id_hospital, fecha, direccion_envio) VALUES ('".$id."', '".$id_objeto."' , '".$cantidad."', '".$id_proveedor."', '".$id_hospital."', '".$fecha."', '".$direccion_envio."')";

$query2 = "DELETE FROM pedidos WHERE id = '$id'";

$query3 = "DELETE FROM pedidos_pendientes WHERE id = '$id'";

$resultado1 = mysqli_query($conexion,$query1);

$resultado2 =  mysqli_query($conexion,$query2);

$resultado3 =  mysqli_query($conexion,$query3);


if($resultado1 && $resultado2 && $resultado3){
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