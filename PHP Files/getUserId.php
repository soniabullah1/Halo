<?php
$username = "";
$password = "";
$database = "";

$link = mysqli_connect("127.0.0.1", $username, $password, $database);

$username = $_REQUEST["Username"];

//$output=array();

if ($r = mysqli_query($link, "SELECT USER_ID FROM USER WHERE USERNAME = '$username'")){

	while($object = mysqli_fetch_object($r)){
		echo $object->USER_ID;
	}

}

elseif(!$link){
echo ("Error Description: " . $link -> error);
echo "Try again";
}

mysqli_close($link);
//echo json_encode($output);
?>

