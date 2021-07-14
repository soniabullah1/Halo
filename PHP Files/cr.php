
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
$phone = $_REQUEST["phone"];
$motivation = $_REQUEST["motivation"];

/* Select queries return a resultset */
if ($r = mysqli_query($link, "INSERT INTO CLOTHING_R (USER_ID, ITEM_NAME, QUANTITY, DESCRIPTION, CONTACT_NUM, MOTIVATION) VALUES ($idNum, '$name',$num,'$description', '$phone', '$motivation')")){
        echo "Successful!";
}

else{
echo ("Error Description: " . $link -> error);
echo "Unsuccessful!";
}


mysqli_close($link);
//echo json_encode($output);
?>

