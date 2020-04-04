<?php
use QueryException;
use Exception;
include 'connexion.php';
$nombre_del_objeto = $_POST['nombre_del_objeto'];
$query1 = "SELECT id FROM objetos WHERE nombre = $nombre_del_objeto"
$resultado = $conexion ->query($query1);
foreach($resultado as r){
    $id_objeto = r;
} 
$cantidad = $_POST['cantidad'];
$nombre_de_usuario =$_POST['nombre_de_usuario'];
$id_de_proveedor = '1';
$usuario_del_hospital = $_POST['usuario'];
$query2 = "SELECT id FROM hospitales WHERE usuario = $usuario_del_hospital"
$resultado = $conexion ->query($query2);
foreach($resultado as id){
    $id_hospital = id;
} 
$fecha = $_POST['fecha'];
$query3 = "INSERT INTO usuarios(nombre, usuario, password, direccion, telefono, email) VALUES ('".$nombre."', '".$usuario."', '".$password."', '".$direccion."', '".$telefono."', '".$email."')"
try{
    $conexion -> query($query3) 
    echo TRUE;
    }
    catch (Exception $e){
        $error_code = $e->errorInfo[1];
        if($error_code == 1062){
            echo FALSE;
        }
        else{
            throw new Exception("Something went wrong")
        }
$conexion -> close();
?>