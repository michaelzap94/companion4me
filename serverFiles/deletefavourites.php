<?php

require("config.inc.php");


       $query = "DELETE FROM contactsSaved WHERE  username = :user AND favourites = :fav  ";
    
     $query_params = array(
      ':user' => $_POST['username'],
        ':fav' => $_POST['favourites']
    
    );
    
     try {
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
        
     
        
    }
    catch (PDOException $ex) {
        //  $response["success"] = 0;
        $response["message"] = "Database Error2. Please Try Again!";
        die(json_encode($response));
    }
        
        
        
    
    
     
     
?>