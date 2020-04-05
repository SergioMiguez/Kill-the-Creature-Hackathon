<?php
include 'connexion.php';
$nombre_del_objeto = $_POST['nombre_del_objeto'];
$query1 = "SELECT id FROM objetos WHERE nombre = $nombre_del_objeto";
$resultado = $conexion ->query($query1);
if($resultado->num_rows){
    $id_objeto = $resultado ->fetch_assoc()["id"];
    echo $id_objeto
}
else{
    throw new Exception("Something went wrong");
}
conexion->close()
?>