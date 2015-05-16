xquery version "3.0";
declare variable $anyo as xs:integer := 2008;

for $ed in doc("Eurovision.xml")/eurovision/edicion[anno/data(@text) = $anyo]
return for $interpretes in $ed//interprete-pais
            order by sum(data($interpretes/voto/@nota)) descending
            return <clasificacion 
                    pais=" {data($interpretes/@pais)} " 
                    cancion=" {data($interpretes/@cancion)} "
                    artista=" {for $art in doc("Eurovision.xml")/eurovision/artista where $art/@cod-artista = $interpretes/@artista return data($art/nombre/@text)} "
                    puntos=" {sum(data($interpretes/voto/@nota))} "
                    />
                    