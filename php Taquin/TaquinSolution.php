<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="fr" xml:lang="fr" xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Taquin</title>
	<link rel="stylesheet" type="text/css" href="Taquin.css" />
	<script type="text/javascript" src="Taquin.js"></script> 
</head>
<body>
<?php
if (!isset ($_SESSION['cmp'])){
session_start();
$_SESSION['cmp']=-1;
}
?>

		<fieldset><legend>Solution</legend>
		<input type="button" value="etape suivante" name="but1" onClick="request()"/>
				<div id="taquin">
				</div>
			</fieldset>
		
</body>
</html>