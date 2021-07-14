<?php
$username = "";
$password = "";
$database = "";

$link = mysqli_connect("127.0.0.1", $username, $password, $database);

$username = $_REQUEST["Username"];
$password = $_REQUEST["Password"];
$hashed = password_hash($password, PASSWORD_DEFAULT);

/* Select queries return a resultset */
if ($r = mysqli_query($link, "UPDATE USER SET PASSWORD = '$hashed' WHERE USERNAME = '$username'")){
	echo "Your Username and Password has been Successfully Updated!";
}

if(!$link){
echo ("Error Description: " . $link -> error);
}

mysqli_close($link);
?>

