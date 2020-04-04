<?php
use QueryException;
use Exception;
include 'connexion.php';
$nombre =$_POST['nombre'];
$usuario =$_POST['usuario'];
$password =$_POST['password'];
$direccion =$_POST['direccion'];
$telefono =$_POST['telefono'];
$email = $_POST['email'];
$consulta = "INSERT INTO hospitales(nombre, usuario, password, direccion, telefono, email) VALUES ('".$nombre."', '".$usuario."', '".$password."', '".$direccion."', '".$telefono."', '".$email."')"
try{
    mysqli_query($conexion,$consulta);
    return TRUE;
    }
    catch (Exception $e){
        $error_code = $e->errorInfo[1];
        if($error_code == 1062){
            return FALSE;
        }
        else{
            throw new Exception("Something went wrong")
        }
$conexion -> close();        
?>

