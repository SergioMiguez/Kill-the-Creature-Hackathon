<?php
include 'connexion.php';
$nombre_material =$_POST['nombre_material'];
//$nombre_material = "ALCOHOL";
$query_material = "INSERT INTO objetos (nombre) VALUES ('".$nombre_material."')";
$res = $conexion ->query($query_material);
if ($res){
    $result = Array();
    $result["success"] = true;
    echo json_encode($result);

}
else {
    $result = Array();
    $result["success"] = false;
    echo json_encode($result);
}
$conexion->close();


?>