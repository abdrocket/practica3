xquery version "3.0";
declare variable $anyo as xs:integer := 2008;

let $ed := doc("Eurovision.xml")/eurovision/edicion[data(@anno) = $anyo]
for $interpretes in $ed//interprete-pais
let $art := doc("Eurovision.xml")/eurovision/artista[@cod-artista = $interpretes/@artista]
order by sum(data($interpretes/voto/@nota)) descending
    return <clasificacion 
        pais=" {data($interpretes/@pais)} " 
        cancion=" {data($interpretes/@cancion)} "
        artista=" {data($art/@nombre)} "
        puntos=" {sum(data($interpretes/voto/@nota))} "
    />