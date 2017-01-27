<?php


require("config.inc.php");

if (!empty($_POST)) {
   
  /*
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
     ( music, movies, sports, food, interests, description, avatar) VALUES ( :mu, :mov, :sp, :fo, :inte, :des, :ava) WHERE (username = :user) ON DUPLICATE KEY UPDATE
     
    
} 

?>
    */
    
    
    


 
      $query = "UPDATE users SET music = :mu, movies= :mov, sports = :sp, food = :fo, hobbies = :inte, description = :des, avatar = :ava WHERE username = :user";
    
     $query_params = array(
          ':user' => $_POST['username'],
      	':mu' => $_POST['music'],
        ':mov' => $_POST['movies'],
        ':sp' => $_POST['sports'],
        ':fo' => $_POST['food'],
        ':inte' => $_POST['hobbies'],
        ':des' => $_POST['description'],
        ':ava' => $_POST['avatar']
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
    
     
     
    
} 



?>