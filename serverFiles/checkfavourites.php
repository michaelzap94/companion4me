<?php


require("config.inc.php");

if (!empty($_POST)) {
   
   /* if (empty($_POST['username']) || empty($_POST['password'])) {
        
        
          $response["success"] = 0;
        $response["message"] = "Please Enter Both a Username and Password.";
        
          die(json_encode($response));
    }
    */
    
      $query        = " SELECT 1 FROM contactsSaved WHERE username = :user AND favourites = :fav ";
    $query_params = array(
        ':user' => $_POST['username'],
         ':fav' => $_POST['favourites']
    );
    
    try {
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
          $response["success"] = 0;
        $response["message"] = "Database Error1. Please Try Again!";
        die(json_encode($response));
    }
    
     $row = $stmt->fetch();
    if ($row) {
           $response["success"] = 0;
        $response["message"] = "I'm sorry, this username is already your friend";
        die(json_encode($response));
    }
    
     else{
    
      $response["success"] = 1;
    $response["message"] = "This username is not your friend yet";
    echo json_encode($response);
    
     }
    
} 

?>