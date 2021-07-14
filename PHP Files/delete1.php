<?php
$username = "s1853416";
$password = "4T$18424!";
$database = "d1853146";

$link = mysqli_connect("127.0.0.1", $username, $password, $database);

$userId = $_REQUEST["UserId"];
$id = (int)$userId;

//$sql = "CALL PROCEDURE DELETE_USER($id)";
//$query = mysqli_query($link, $sql);

/* Select queries return a resultset */
if ($query = mysqli_query($link,"CALL DELETE_USER($id)")){
	echo "Your account was successfully deleted. Thank you for using Halo!";
}
else{
	echo "Sorry, this did not work. Please try again.";
}

if(!$link){
	echo ("Error Description: " . $link -> error);
}

mysqli_close($link);
?>

