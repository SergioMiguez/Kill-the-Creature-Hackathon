<?php
include 'connexion.php';
$id_pedido = intval($_POST['id_pedido']);
$username = $_POST['username'];
//$id_pedido = 6;
//$usuario = "test";
$queryProveedor = "SELECT id FROM proveedores WHERE usuario = '$username'";

$resultadoProveedor = $conexion ->query($queryProveedor);

if(mysqli_num_rows($resultadoProveedor)){
    $id_proveedor = $resultadoProveedor ->fetch_assoc()["id"];
    //echo $id_hospital;
}
else{
    throw new Exception("FATAL ERROR: Hospital not in our database");
}
// CANTIDAD

$queryidHospital= "SELECT id_hospital FROM pedidos WHERE id = '$id_pedido'";

$resultadoidHospital = $conexion ->query($queryidHospital);

if(mysqli_num_rows($resultadoidHospital)){
    $id_hospital = intval($resultadoidHospital ->fetch_assoc()["id_hospital"]);
    //echo $id_hospital;
}

// CANTIDAD

$queryCantidad = "SELECT cantidad FROM pedidos WHERE id = '$id_pedido'";

$resultadoCantidad = $conexion ->query($queryCantidad);

if(mysqli_num_rows($resultadoCantidad)){
    $cantidad = intval($resultadoCantidad ->fetch_assoc()["cantidad"]);
    //echo $id_hospital;
}
else{
    throw new Exception("FATAL ERROR: Hospital not in our database");
}

// ID OBJETO

$queryObjeto = "SELECT id_objeto FROM pedidos WHERE id = '$id_pedido' ";

$resultadoObjeto = $conexion ->query($queryObjeto);

if(mysqli_num_rows($resultadoObjeto)){
    $id_objeto = intval($resultadoObjeto ->fetch_assoc()["id_objeto"]);
    //echo $id_hospital;
}
else{
    throw new Exception("FATAL ERROR: Hospital not in our database");
}

// FECHA

$queryFecha = "SELECT fecha FROM pedidos WHERE id = '$id_pedido'";

$resultadoFecha = $conexion ->query($queryFecha);

if(mysqli_num_rows($resultadoFecha)){
    $fecha = $resultadoFecha ->fetch_assoc()["fecha"];
}
else{
    throw new Exception("FATAL ERROR: Hospital not in our database");
}

// DIRECCION
$queryDireccion = "SELECT direccion_envio FROM pedidos WHERE id = '$id_pedido'";

$resultadoDireccion = $conexion ->query($queryDireccion);

if(mysqli_num_rows($resultadoDireccion)){
    $direccion_envio = $resultadoDireccion ->fetch_assoc()["direccion_envio"];
    //echo $id_hospital;
}
else{
    throw new Exception("FATAL ERROR: Hospital not in our database");
}

$query1 = "INSERT INTO pedidos_pendientes VALUES ('".$id_pedido."', '".$id_objeto."' , '".$cantidad."', '".$id_proveedor."', '".$id_hospital."', '".$fecha."', '".$direccion_envio."')";



$resultado1 = mysqli_query($conexion,$query1);



if($resultado1){
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