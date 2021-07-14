<?php
$username = "";
$password = "";
$database = "";

$link = mysqli_connect("127.0.0.1", $username, $password, $databse);

if(isset($_REQUEST["Password"])){
	$user = $_REQUEST["Username"];
	$password = $_REQUEST["Password"];
}

$sql = "SELECT * FROM USER WHERE USERNAME = '$user';";

$output = array();

if($result = mysqli_query($link,$sql)){
    while($row = $result ->fetch_assoc()){
        $hashedPassword = $row["PASSWORD"];
	$id = $row["USER_ID"];
    }
}
else{
    echo "Sorry that did not work. Please try again";
}


if(!$link){
	echo "Sorry that did not work. Please try again.";
}

mysqli_close($link);

if(password_verify($password,$hashedPassword)){
    echo "Password Valid!";
    echo "\n".$id;
}
else{
    echo "Invalid Password!";
}
?>
