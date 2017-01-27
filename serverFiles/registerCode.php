<?php


require("config.inc.php");

if (!empty($_POST)) {
   
   
      $query        = " SELECT 1 FROM codetable WHERE username = :user";
    $query_params = array(
        ':user' => $_POST['username']
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
          
        
          
          
          $query = "UPDATE codetable SET verificationcode = :pass WHERE username = :user";
    
     $query_params = array(
        ':user' => $_POST['username'],
        ':pass' => $_POST['verificationcode']
      
    );
    
     try {
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
          $response["success"] = 0;
        $response["message"] = "Database Error2. Please Try Again pdopdopodpod!";
        die(json_encode($response));
    }
          
    
    }
    
    else{
      $query = "INSERT INTO codetable ( username, verificationcode ) VALUES ( :user, :pass) ";
    
     $query_params = array(
        ':user' => $_POST['username'],
        ':pass' => $_POST['verificationcode']
      
    );
    
     try {
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
          $response["success"] = 0;
        $response["message"] = "Database Error2. Please Try Again!insertinsetinset";
        die(json_encode($response));
    }
    
      $response["success"] = 1;
    $response["message"] = "Username Successfully Added!";
    echo json_encode($response);
    
     }
    
} 

?>