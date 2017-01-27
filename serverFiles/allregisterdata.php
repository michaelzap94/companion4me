<?php


require("config.inc.php");

//initial query

if (!empty($_POST['name'])) {
  
    $query        = " SELECT * FROM users WHERE name LIKE :nam OR nationality = :natio OR (:min < age AND age < :max) OR education = :ed OR gender = :ge OR  music LIKE :mu OR movies LIKE :mov OR sports LIKE :sp OR food LIKE :fo OR hobbies LIKE :inte ";

//name should not allow empty space or empty parameters because that returns all the users

  // add the input variables from android
   
 // $string = str_replace(' ', '',$_POST['name'] );
  $string = preg_replace('/\s+/', '',$_POST['name']);
 
    $query_params = array(
      //  ':user' => $_POST['username'], not needed
        
        // eg '%'.$_POST['name'].'%' to wildcard in pdo
      ':nam' => '%'.$string.'%',
        ':natio' => $_POST['nationality'],
        //':ag' => $_POST['age'],
      //  ':nospaces' => '%'." ".'%',
       //      ':nospaces' => '%'.$_POST['space'].'%',
    // ':empt' => '%'.$_POST['empty'].'%',

      
       ':max' => (int) $_POST['maximum'],
        ':min' => (int) $_POST['minimum'],
          ':ed' =>  $_POST['education'],
            ':ge' =>  $_POST['gender'],
            ':mu' => '%'.$_POST['music'].'%',
        ':mov' => '%'.$_POST['movies'].'%',
        ':sp' => '%'.$_POST['sports'].'%',
        ':fo' => '%'.$_POST['food'].'%',
        ':inte' => '%'.$_POST['hobbies'].'%',
            
       
          //    ':coun' => (int) $_POST['country'],       IMPLEMENT THIS WHEN COUNTRY DONE
           //     ':cit' => (int) $_POST['city'],   IMPLEMENT THIS WHEN CITY DONE

    );
    
 


//execute query
try {
    $stmt   = $db->prepare($query);
    $result = $stmt->execute($query_params);
}
catch (PDOException $ex) {
    $response["success"] = 0;
    $response["message"] = "Database Error!";
    die(json_encode($response));
}



// Finally, we can retrieve all of the found rows into an array using fetchAll 
$rows = $stmt->fetchAll();

if ($rows) {
    $response["success"] = 1;
    $response["message"] = "Post Available!";
    $response["userdataC"]   = array();
    
    foreach ($rows as $row) {
        $post             = array();
        $post["username"] = $row["username"];
        $post["name"]    = $row["name"];
        $post["age"]  = $row["age"];
          $post["nationality"]  = $row["nationality"];
                            $post["avatar"]  = $row["avatar"];

        
        //update our repsonse JSON data
        array_push($response["userdataC"], $post);
    }
    
    // echoing JSON response
    echo json_encode($response);
    
    
} else {
    $response["success"] = 0;
    $response["message"] = "No Post Available!";
    die(json_encode($response));
}
}

else{

  
    $query        = " SELECT * FROM users WHERE nationality = :natio OR (:min < age AND age < :max) OR education = :ed OR gender = :ge OR  music LIKE :mu OR movies LIKE :mov OR sports LIKE :sp OR food LIKE :fo OR hobbies LIKE :inte ";

//name should not allow empty space or empty parameters because that returns all the users

  // add the input variables from android
   
 // $string = str_replace(' ', '',$_POST['name'] );
  //$string = preg_replace('/\s+/', '',$_POST['name']);
 
    $query_params = array(
      //  ':user' => $_POST['username'], not needed
        
        // eg '%'.$_POST['name'].'%' to wildcard in pdo
      //':nam' => '%'.$string.'%',
        ':natio' => $_POST['nationality'],
        //':ag' => $_POST['age'],
      //  ':nospaces' => '%'." ".'%',
       //      ':nospaces' => '%'.$_POST['space'].'%',
    // ':empt' => '%'.$_POST['empty'].'%',

      
       ':max' => (int) $_POST['maximum'],
        ':min' => (int) $_POST['minimum'],
          ':ed' =>  $_POST['education'],
            ':ge' =>  $_POST['gender'],
          ':mu' => '%'.$_POST['music'].'%',
        ':mov' => '%'.$_POST['movies'].'%',
        ':sp' => '%'.$_POST['sports'].'%',
        ':fo' => '%'.$_POST['food'].'%',
        ':inte' => '%'.$_POST['hobbies'].'%',
          //    ':coun' => (int) $_POST['country'],       IMPLEMENT THIS WHEN COUNTRY DONE
           //     ':cit' => (int) $_POST['city'],   IMPLEMENT THIS WHEN CITY DONE

    );
    
 


//execute query
try {
    $stmt   = $db->prepare($query);
    $result = $stmt->execute($query_params);
}
catch (PDOException $ex) {
    $response["success"] = 0;
    $response["message"] = "Database Error!";
    die(json_encode($response));
}



// Finally, we can retrieve all of the found rows into an array using fetchAll 
$rows = $stmt->fetchAll();

if ($rows) {
    $response["success"] = 1;
    $response["message"] = "Post Available!";
    $response["userdataC"]   = array();
    
    foreach ($rows as $row) {
        $post             = array();
        $post["username"] = $row["username"];
        $post["name"]    = $row["name"];
        $post["age"]  = $row["age"];
          $post["nationality"]  = $row["nationality"];
                    $post["avatar"]  = $row["avatar"];

        
        
        //update our repsonse JSON data
        array_push($response["userdataC"], $post);
    }
    
    // echoing JSON response
    echo json_encode($response);
    
    
} else {
    $response["success"] = 0;
    $response["message"] = "No Post Available!";
    die(json_encode($response));
}
}


?>