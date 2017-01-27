<?php


require("config.inc.php");

if (!empty($_POST)) {
   
     
      $query = "UPDATE users SET name=:nam, 
      age=:ag, 
      nationality=:nation, 
        education=:ed, 
      gender =:ge,
      country = :coun,
      city = :cit
    
      WHERE username = :user ";
     
          
    
     $query_params = array(
        ':user' => $_POST['username'],
        ':nam' => $_POST['name'],
        ':ag' => $_POST['age'],
         ':nation' => $_POST['nationality'],
                  ':ed' => $_POST['education'],
            ':ge' => $_POST['gender'],
             ':coun' =>  $_POST['country'],       
               ':cit' =>  $_POST['city']
        
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
    $response["message"] = "Username Successfully edited!";
    echo json_encode($response);
    
     
    
} 

?>