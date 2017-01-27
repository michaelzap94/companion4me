<?php


require("config.inc.php");

if (!empty($_POST)) {
   
     
      $query = "UPDATE users SET 
      answer=:ans, 
      question=:que 
     
      WHERE username = :user ";
     
          
    
     $query_params = array(
        ':user' => $_POST['username'],
         ':ans' => $_POST['answer'],
         ':que' => $_POST['question']
          
    );
    
     try {
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
          $response["success"] = 0;
        $response["message"] = "Database Error2. Please Try Again!";
        die(json_encode($response));
    }
    
      $response["success"] = 1;
    $response["message"] = "Security Details Successfully edited!";
    echo json_encode($response);
    
     
    
} 

?>