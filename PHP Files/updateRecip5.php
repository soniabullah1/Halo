<?php
$username = "s1853416";
$password = "4T$18424!";
$database = "d1853146";

$link = mysqli_connect("127.0.0.1", $username, $password, $database);

$recipId = $_REQUEST["ID"];
$id = (int)$recipId;
$numItems = $_REQUEST["Num"];
$num = (int)$numItems;

//$output=array();
/* Select queries return a resultset */
if ($r = mysqli_query($link, "UPDATE ANIMAL_CARE_R SET QUANTITY = QUANTITY - $num WHERE USER_ID = $id")){
        echo "Success!";
}

if(!$link){
echo ("Error Description: " . $link -> error);
}

mysqli_close($link);
//echo json_encode($output);
?>


