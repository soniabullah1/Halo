<?php
$username = "s1853416";
$password = "4T$18424!";
$database = "d1853146";

$link = mysqli_connect("127.0.0.1", $username, $password, $database);

$username = $_REQUEST["Username"];
$password = $_REQUEST["Password"];
$hashed = password_hash($password, PASSWORD_DEFAULT);

if ($r = mysqli_query($link, "INSERT INTO USER (USERNAME, PASSWORD) VALUES ('$username', '$hashed')")){
	echo "Your Username and Password has been Successfully Created!";
}
else{
	echo "Sorry but this Username is taken. Please create another username, try using numbers and special characters.";
}

if(!$link){
	echo "Sorry but this username has already been used. Please create another username, try using numbers and special characters.";
	echo ("Error Description: " . $link -> error);
}

mysqli_close($link);
?>

