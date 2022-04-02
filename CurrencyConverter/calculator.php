<?php

include ("db_info.php");

$amount=intval($_GET["amount"]);
$currency_type=$_GET["currency_type"];
$response = [];

//changing them to json
$response["amount_convert"]=$amount;
$response["currency_type"]=$currency_type;
$data_result = json_encode($response);
echo $data_result;

$query=$mysqli->prepare("INSERT INTO convert_currencies(amount,amount_type) VALUES (?,?)");
$query->bind_param("is",$amount,$currency_type);
$query->execute();

?>