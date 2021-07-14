<?php
$username = "";
$password = "";
$database = "";

$link = mysqli_connect("127.0.0.1", $username, $password, $database);

$item = $_REQUEST["Item"];

$output=array();
/* Select queries return a resultset */
if ($r = mysqli_query($link, "SELECT * FROM OTHER_R WHERE ITEM_NAME = '$item' AND QUANTITY > 0")){

        while($row=$r->fetch_assoc()){
                $output[]=$row;
        }
}

if(!$link){
echo ("Error Description: " . $link -> error);
}

mysqli_close($link);
echo json_encode($output);
?>

