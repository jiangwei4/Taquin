<?php
include('Stats.php');
for ($i=0;$i<$nbr_info = (sizeof($info));$i=($i+7)){
echo '<table><tr><caption colspan="6">'.$info[$i].'</caption></tr>
<tr><th rowspan="2">taille de la solution</th><th rowspan="2">nombre(s) de position parcourues</th><th colspan="4">temps d execution</th></tr>
<tr><th>heure(s)</th><th>minute(s)</th><th>seconde(s)</th><th>milliseconde(s)</th></tr>';
if(strcmp($info[$i+1], " Delai d execution depasse") == 0){
echo'<tr><td colspan="6"><center>'.$info[$i+1].'</center></td></tr></table></br>';
}else{
if(strcmp($info[$i+1], "L algorithme n a pas abouti a une solution") == 0){
echo'<tr><td colspan="6"><center>'.$info[$i+1].'</center></td></tr></table></br>';
}else{
echo'<tr><td><center>'.$info[$i+1].'</center></td><td><center>'.$info[$i+2].'</center></td>
<td><center>'.$info[$i+3].'</center></td><td><center>'.$info[$i+4].'</center></td><td><center>'.$info[$i+5].'</center></td><td><center>'.$info[$i+6].'</center></td></tr></table></br>';
}}}
?>