<?php
$username = "";
$password = "";
$database = "";

$link = mysqli_connect("127.0.0.1", $username, $password, $database);

$id = $_REQUEST["id"];
$idNum= (int)$id;
$name = $_REQUEST["name"];
$description = $_REQUEST["description"];
$quantity = $_REQUEST["quantity"];
$num= (int)$quantity;

/* Select queries return a resultset */
if ($r = mysqli_query($link, "INSERT INTO HYGIENE_D (USER_ID, ITEM_NAME, QUANTITY, DESCRIPTION) VALUES ($idNum, '$name', $num,'$description')")){
        echo "Successful!";
}

else{
        echo ("Error Description: " . $link -> error);
        echo "Unsuccessful!";
}

mysqli_close($link);

?>

