<?php
$username = "";
$password = "";
$database = "";

$link = mysqli_connect("127.0.0.1", $username, $password, $database);

$id = $_REQUEST["id"];
$idNum = (int)$id;
$username = $_REQUEST["Username"];
$password = $_REQUEST["Password"];
$hashed = password_hash($password, PASSWORD_DEFAULT);

/* Select queries return a resultset */
if ($r = mysqli_query($link, "UPDATE USER SET PASSWORD = '$hashed', USERNAME = '$username' WHERE USER_ID = $idNum")){
        echo "Your Username and Password has been Successfully Updated!";
}
else{
	echo "Your username is invalid. Please try creating another username using numbers or special characters.";
}

if(!$link){
	echo "Your username is invalid. Please try creating another username using numbers or special characters.";
	echo ("Error Description: " . $link -> error);
}

mysqli_close($link);
?>

