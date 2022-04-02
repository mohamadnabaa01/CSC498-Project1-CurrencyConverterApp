<?php

include ("db_info.php");

$amount=intval($_GET["amount"]);
$currency_type=$_GET["currency_type"];
$current_rate=intval($_GET["current_rate"]);
$response = [];

//changing them to json
$response["amount_convert"]=$amount;
$response["currency_type"]=$currency_type;
$response["current_rate"]=$current_rate;


$query=$mysqli->prepare("INSERT INTO convert_currencies(amount,amount_type,current_rate) VALUES (?,?,?)");
$query->bind_param("isi",$amount,$currency_type,$current_rate);
$query->execute();
if($currency_type=="USD"){
$result=$amount*$current_rate;
}else if($currency_type="L.L."){
$result=$amount/$current_rate;
}
$response["result"]=$result;
$data_result = json_encode($response);
echo $data_result;

?>