<?php

$dbhost = "127.0.0.1";
$dbuser = "st";
$dbpassword = "";
$db = "st";
$table = "smartthings";

$sql = "INSERT INTO ".$table;
$fields = array();
$values = array();

// connect to database
        echo "<br> host: $dbhost, user: $dbuser, password: $dbpassword, database: $db<br>";
        $mysqli = new mysqli("$dbhost", "$dbuser", "$dbpassword", "$db");

        /* check connection */
        if ($mysqli->connect_errno) {
                printf("Connect failed: %s\n", $mysqli->connect_error);
                exit();
        }

foreach($_GET as $key=>$val) {

$column = $key;
echo "<br>$key = $val<br>";
   $fields[] = "`$key`";
   $values[] = "'".$val."'";

// create column
        echo "trying to create column $column in table $table";
        if ($mysqli->query("alter table $table add $column varchar (20) default ''") === TRUE) {
                echo "<br>column $column created sucessfully<br>";
        }
}

// send data

$sql .= " (".implode(", ", $fields).") VALUES (".implode(", ", $values).")";
echo "<br>$sql<br>";
if ($mysqli->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $mysqli->error;
}

$mysqli->close();

?>