<?php
$username = "";
$password = "";
$database = "";

$link = mysqli_connect("127.0.0.1", $username, $password, $database);

//$category = $_REQUEST["Category"];

$output=array();

/* Select queries return a resultset */
if ($result = mysqli_query($link, "SELECT * FROM HYGIENE_R WHERE QUANTITY > -1 ORDER BY QUANTITY")) {
        while ($row=$result->fetch_assoc()){
        $output[]=$row;
        }
}

if(!$link){
        echo ("Error Description: " . $link -> error);
}

mysqli_close($link);
echo json_encode($output);
?>
