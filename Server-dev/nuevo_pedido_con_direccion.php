<?php
include 'connexion.php';
//$nombre_del_objeto = $_POST['nombre_del_objeto'];
//$usuario = $_POST['usuario'];

$nombre_del_objeto = "Buzo 3B";
$usuario = "test1";

$queryObjetos = "SELECT id FROM objetos WHERE nombre = '$nombre_del_objeto'";

$resultadoObjetos = $conexion ->query($queryObjetos);

if(mysqli_num_rows($resultadoObjetos)){
    $id_objeto = $resultadoObjetos ->fetch_assoc()["id"];
    echo $id_objeto;
}
else{
    throw new Exception("Something went wrong");
}

$queryHospital = "SELECT id FROM hospitales WHERE usuario = '$usuario'";

$resultadoHospital = $conexion ->query($queryHospital);

if(mysqli_num_rows($resultadoHospital)){
    $id_hospital = $resultadoHospital ->fetch_assoc()["id"];
    echo $id_hospital;
}
else{
    throw new Exception("Something went wrong");
}

$conexion->close();
?>