<?php


require("config.inc.php");

if (!empty($_POST)) {
   
 
    
      $query        = " SELECT 1 FROM contactsBlocked WHERE username = :user AND blocked = :bl ";
    $query_params = array(
        ':user' => $_POST['username'],
         ':bl' => $_POST['blocked']
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
        $response["message"] = "This username is Blocked";
        die(json_encode($response));
    }
    
      $query = "INSERT INTO contactsBlocked ( username, blocked, name, avatar, age ) VALUES ( :user, :bl, :nam, :ava, :ag ) ";
    
     $query_params = array(
        ':user' => $_POST['username'],
        ':bl' => $_POST['blocked'],
                ':nam' => $_POST['name'],
        ':ava' => $_POST['avatar'],
        ':ag' => $_POST['age']

        
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
    $response["message"] = "Contact Successfully Blocked!";
    echo json_encode($response);
    
     
    
} 

?>