<?php
include 'connexion.php';
$usuario =$_POST['usuario'];
$password=$_POST['password'];
$resultado = $conexion ->query("SELECT * FROM usuarios WHERE usuario = $usuario AND password =$password");
if ($resultado ->num_rows){
    $result[] =array_map('resultado',1);
}
else{
    $result[] =array_map('resultado',0);

}
echo json_encode($result);
$conexion ->close();
?>