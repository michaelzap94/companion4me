<?php
	require("config.inc.php");

	if(!empty($_POST)){
		$query = "INSERT INTO Messages ( FromUsername, ToUsername, MessageBody ) VALUES ( :FID, :TID, :MsgBdy ) ";
		
		$query_params = array(
			':FID' => $_POST['FromUser'],
			':TID' => $_POST['ToUser'],
			':MsgBdy' => $_POST['MessageBody']
			
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
?>