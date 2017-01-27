<?php


require("config.inc.php");

if (!empty($_POST)) {
   
    if (empty($_POST['username']) || empty($_POST['password'])) {
        
        
          $response["success"] = 0;
        $response["message"] = "Please Enter Both a Username and Password.";
        
          die(json_encode($response));
    }
    
      $query        = " SELECT 1 FROM users WHERE username = :user";
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
           $response["success"] = 0;
        $response["message"] = "I'm sorry, this username is already in use";
        die(json_encode($response));
    }
    
      $query = "INSERT INTO users ( username, password, name, age, nationality, answer, question, education, gender, country, city ) VALUES ( :user, :pass, :nam, :ag, :nation, :ans, :que, :ed, :ge, :coun, :cit ) ";
    
     $query_params = array(
        ':user' => $_POST['username'],
        ':pass' => $_POST['password'],
        ':nam' => $_POST['name'],
        ':ag' => $_POST['age'],
         ':nation' => $_POST['nationality'],
         ':ans' => $_POST['answer'],
         ':que' => $_POST['question'],
           ':ed' => $_POST['education'],
            ':ge' => $_POST['gender'],
            ':coun' => $_POST['country'],     
               ':cit' => $_POST['city']
        
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
    $response["message"] = "Username Successfully Added!";
    echo json_encode($response);
    
     
    
} 

?>