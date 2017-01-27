<?php


require("config.inc.php");

//initial query


  
    $query        = " SELECT * FROM contactsSaved WHERE username = :user ";





  $query_params = array(
        ':user' => $_POST['username']
    );
    
     try {
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    }
catch (PDOException $ex) {
    $response["success"] = 0;
    $response["message"] = "Database Error!";
    die(json_encode($response));
}



// Finally, we can retrieve all of the found rows into an array using fetchAll 
$rows = $stmt->fetchAll();

if ($rows) {
    $response["success"] = 1;
    $response["message"] = "Post Available!";
    $response["userdataC"]   = array();
    
    foreach ($rows as $row) {
        $post             = array();
        $post["username"] = $row["username"];
        $post["favourites"]    = $row["favourites"];
          $post["name"]  = $row["name"];
                  $post["avatar"]  = $row["avatar"];
          $post["age"]  = $row["age"];

        
        //update our repsonse JSON data
        array_push($response["userdataC"], $post);
    }
    
    // echoing JSON response
    echo json_encode($response);
    
    
} else {
    $response["success"] = 0;
    $response["message"] = "No Post Available!";
    die(json_encode($response));
}

?>