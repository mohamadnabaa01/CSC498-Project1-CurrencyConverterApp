<?php

$db_host = "localhost";
$db_user = "root";
$db_pass = "j.007-ham";
$db_name = "currency_converter_db";

$mysqli = new mysqli($db_host, $db_user, $db_pass, $db_name);

if(mysqli_connect_errno()){
    die("Connection to database failed!");
}

?>