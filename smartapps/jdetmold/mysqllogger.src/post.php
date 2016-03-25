<?php
echo " ";
$dbhost = "127.0.0.1";
$dbuser = "st";
$dbpassword = "";
$db = "st";
$table = "smartthings";

$sql = "INSERT INTO ".$table;
$fields = array();
$values = array();



foreach($_GET as $key=>$val) {
	
	$column = $key;
	echo "<br>$key = $val<br>";
	$fields[] = "`$key`";
	$values[] = "'".$val."'";
	
	$mysqli = connectdb($dbhost, $dbuser, $dbpassword, $db); // connect to database
	createdbcol($mysqli, $column, $table); // create columns
}

senddbdata($sql,$mysqli, $fields, $values); // send data
closedb($mysqli); // close database
// connect to database
function connectdb($dbhost, $dbuser, $dbpassword, $db){
        $mysqli = new mysqli("$dbhost", "$dbuser", "$dbpassword", "$db");

        /* check connection */
        if ($mysqli->connect_errno) {
                printf("Connect failed: %s\n", $mysqli->connect_error);
                exit();
        }
		return $mysqli;
}



// create column
function createdbcol($mysqli, $column, $table){
        echo "trying to create column $column in table $table";
        if ($mysqli->query("alter table $table add $column varchar (20) default ''") === TRUE) {
                echo "<br>column $column created sucessfully<br>";
        }
}

// send data
function senddbdata($sql,$mysqli, $fields, $values){
	$sql .= " (".implode(", ", $fields).") VALUES (".implode(", ", $values).")";
//	echo "<br>$sql<br>";
	if ($mysqli->query($sql) === TRUE) {
		echo "New record created successfully";
	} else {
		echo "Error: " . $sql . "<br>" . $mysqli->error;
	}
}

function closedb($mysqli){
	$mysqli->close();
}

?>