<?php

include("db_info.php");
$query = $mysqli->prepare("SELECT * FROM rates");
$query->execute();

?>