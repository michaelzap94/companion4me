<?php


require("config.inc.php");

if (!empty($_POST)) {
   
 
    
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
        $response["message"] = "This username is already your friend";
        die(json_encode($response));
    }
    
      $query = "INSERT INTO contactsSaved ( username, favourites, selected, name, avatar, age ) VALUES ( :user, :fav, :sel, :nam, :ava, :ag ) ";
    
     $query_params = array(
        ':user' => $_POST['username'],
        ':fav' => $_POST['favourites'],
        ':sel' => $_POST['selected'],
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
    $response["message"] = "Friend Successfully Added!";
    echo json_encode($response);
    
     
    
} 

?>