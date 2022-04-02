<?php

include ("db_info.php");

$amount=$_GET["amount"];
$currency_type=$_GET["currency_type"];
$response = [];

//changing them to json
$response["amount_convert"]=$amount;
$response["currency_type"]=$currency_type;
$data_result = json_encode($response);
echo $data_result;

?>