<?php
session_start();
$_SESSION['cmp']=$_SESSION['cmp']+1;
include('TaquinDeplacement.php');

if($_SESSION['cmp']==0){  
	echo '<p>Etat initiale</p>';
} else {
	echo '<p>Etape n°'.$_SESSION['cmp'].' sur '.intval((sizeof($array)/($hauteur*$largeur))-1).'</p>';
}

affichergrille();

if  ($_SESSION['cmp']>((sizeof($array)/($hauteur*$largeur))-2)){
	echo "<table><caption>résolution terminé</caption></table>";
	$_SESSION['cmp']=$_SESSION['cmp']-1;
}

function affichergrille(){
include('TaquinDeplacement.php');
echo '<table>'; 
	for ($i=0;$i<$hauteur;$i++){
		echo'<tr>';
		for ($j=0;$j<$largeur;$j++){
			if($array[(($i*$largeur+$j)+$_SESSION['cmp']*$hauteur*$largeur)]==0){
			echo'<td>&nbsp;</td>';
			}else{
			echo'<td>'.$array[(($i*$largeur+$j)+$_SESSION['cmp']*$hauteur*$largeur)].'</td>';
			}
		}
		echo'</tr>';
	}
	echo '</table>';
}
?>