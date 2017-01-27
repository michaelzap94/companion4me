<?php

require("config.inc.php");


       $query = "DELETE FROM contactsBlocked WHERE  username = :user AND blocked = :bl  ";
    
     $query_params = array(
      ':user' => $_POST['username'],
        ':bl' => $_POST['blocked']
    
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