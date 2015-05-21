xquery version "3.0";
declare variable $anyo as xs:integer external;

let $ed := doc("Eurovision.xml")/eurovision/edicion[data(@anno) = $anyo]
for $interpretes in $ed//interprete-pais
let $nota := sum(data($interpretes/voto/@nota))
order by $nota descending
return <clasificacion 
    pais=" {data($interpretes/@pais)} " 
    cancion=" {data($interpretes/@cancion)} "
    artista=" {let $art := doc("Eurovision.xml")//artista[@cod-artista=$interpretes/@artista] return data($art/@nombre)} "
    puntos=" {$nota} "
    />