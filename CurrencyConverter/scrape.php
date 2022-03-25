<?php

include ("db_info.php");
$scrape = curl_init();
curl_setopt($scrape, CURLOPT_URL, "https://lirarate.org/wp-json/lirarate/v2/rates?currency=LBP");
curl_setopt($scrape, CURLOPT_RETURNTRANSFER, true);
$result = curl_exec($scrape);
$data = json_encode($result);
$string_data = explode(",", $data);
$rate_string = explode("]", $string_data[sizeof($string_data)-1]);
$current_rate = $rate_string[0];
echo $current_rate;
$mysql = $mysqli->prepare("INSERT INTO rates(rate) VALUES (?)");
$mysql->bind_param('i', $current_rate);
$mysql->execute();
$mysql2 = $mysqli->prepare("SELECT rate FROM rates WHERE ID= (SELECT COUNT(rate) FROM rates) as last_rate");
$mysql2->execute();
curl_close($scrape);
?>