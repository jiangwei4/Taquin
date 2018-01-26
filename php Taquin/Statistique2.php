<?php
include('StatsMoyenne.php');
$nombreInfo=8;
$taille=((sizeof($info))/$nombreInfo);
echo '<table><tr><caption colspan="6">INFORMATIONS</caption></tr><tr><th rowspan="2">type d algorithme</th><th rowspan="2">nombre de taquin resolu</th><th rowspan="2">taille de la solution</th><th rowspan="2">nombre(s) de position parcourues</th><th colspan="4">temps d execution</th></tr>
	<tr><th>heure(s)</th><th>minute(s)</th><th>seconde(s)</th><th>milliseconde(s)</th></tr>';
for($i=0;$i<$taille;$i++){
	echo'<tr><td BGCOLOR="YellowGreen"><span style="color: blue"><center>'.$info[$i*$nombreInfo].'</center></span></td>';
	for ($j=1;$j<$nombreInfo;$j++){
		echo'<td><center>'.$info[$j+$i*$nombreInfo].'</center></td>';
	}
	echo'</tr>';
}
echo'</table>';
?>
