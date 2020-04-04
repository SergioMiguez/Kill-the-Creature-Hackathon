<?php
include 'connexion.php';
$nombre = $_POST['nombre'];
$consulta = "INSERT INTO objetos(nombre) VALUES ('".$nombre."')"
try{
    mysqli_query($conexion,$consulta);
    return TRUE;
    }
    catch (QueryException $e){
        $error_code = $e->errorInfo[1];
        if($error_code == 1062){
            return FALSE;
        }
$conexion -> close();
?>
