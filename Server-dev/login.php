<?php
include 'connexion.php';


if(isset($_POST['usuario'])) {
	$usuario = $_POST['usuario'];
}

if(isset($_POST['password'])){
	$password = $_POST['password'];
}
//$usuario ="fgasdfas";
//$password="test";


$query = ("SELECT * FROM hospitales WHERE usuario = '$usuario' AND password ='$password'");

$resultado = mysqli_query($conexion,$query);

if($fila = $resultado ->fetch_assoc()){
    echo json_encode($fila,JSON_UNESCAPED_UNICODE);
}

$conexion ->close();
?>