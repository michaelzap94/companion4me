<?php


require("config.inc.php");

//initial query

if (!empty($_POST)) {
  
    $query        = " SELECT * FROM users WHERE username = :user";
  
    $query_params = array(
        ':user' => $_POST['username']
    );
    

//execute query
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
    $response["userdata"]   = array();
    
    foreach ($rows as $row) {
        $post             = array();
        $post["username"] = $row["username"];
        $post["name"]    = $row["name"];
        $post["age"]  = $row["age"];
          $post["nationality"]  = $row["nationality"];
         $post["education"]  = $row["education"];
          $post["gender"]  = $row["gender"];
           $post["music"]  = $row["music"];
            $post["movies"]  = $row["movies"];
             $post["food"]  = $row["food"];
              $post["sports"]  = $row["sports"];
               $post["description"]  = $row["description"];
                $post["hobbies"]  = $row["hobbies"];
                        $post["avatar"]  = $row["avatar"];
                                                $post["country"]  = $row["country"];
					  $post["city"]  = $row["city"];


        //update our repsonse JSON data
        array_push($response["userdata"], $post);
    }
    
    // echoing JSON response
    echo json_encode($response);
    
    
} else {
    $response["success"] = 0;
    $response["message"] = "No Post Available!";
    die(json_encode($response));
}
}

?>