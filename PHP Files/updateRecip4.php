<?php
$username = "";
$password = "";
$database = "";

$link = mysqli_connect("127.0.0.1", $username, $password, $database);

$recipId = $_REQUEST["ID"];
$id = (int)$recipId;
$numItems = $_REQUEST["Num"];
$num = (int)$numItems;

/* Select queries return a resultset */
if ($r = mysqli_query($link, "UPDATE STATIONERY_R SET QUANTITY = QUANTITY - $num WHERE USER_ID = $id")){
        echo "Success!";
}

if(!$link){
echo ("Error Description: " . $link -> error);
}

mysqli_close($link);

?>


