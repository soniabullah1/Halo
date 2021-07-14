
<?php
$username = "";
$password = "";
$database = "";

$link = mysqli_connect("127.0.0.1", $username, $password, $database);

$userId = $_REQUEST["UserId"];
$id = (int)$userId;

$output=array();
/* Select queries return a resultset */
if ($r = mysqli_query($link, "SELECT * FROM Donator WHERE USER_ID = $id")){

        while($row=$r->fetch_assoc()){
                $output[]=$row;
        }

	if(count($output) > 0){
    		echo "True";
	}

	else{
    		echo "False";
	}
}

if(!$link){
	echo ("Error Description: " . $link -> error);
}

mysqli_close($link);
?>
