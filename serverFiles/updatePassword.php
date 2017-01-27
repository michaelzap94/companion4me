<?php


require("config.inc.php");

if (!empty($_POST)) {
   
  
      $query = "UPDATE users SET password =:pass WHERE username = :user ";
    
     $query_params = array(
        ':user' => $_POST['username'],
        ':pass' => $_POST['password']
     
        
    );
    
     
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    
      $response["success"] = 1;
    $response["message"] = "Username Successfully Added!";
    echo json_encode($response);
    
     
    
} 

?>