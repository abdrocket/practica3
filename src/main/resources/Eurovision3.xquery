xquery version "3.0";
declare variable $anyo as xs:integer := 2008;

for $ed in doc("Eurovision.xml")/eurovision/edicion[anno/data(@text) = $anyo]
let $nomPais := data($ed/pais/@text)
let $nomCiudad := data($ed/ciudad/@text)
return  <body>
    <h1>{concat($nomPais, " (", $nomCiudad, ")")}</h1>
    {
    <ol>{
        for $interpretacion in $ed//interprete-pais
        let $art-pais := data($interpretacion/@pais)
        let $art-cod := data($interpretacion/@artista)
        let $art-cancion := data($interpretacion/@cancion)
        return 
            for $artista in doc("Eurovision.xml")/eurovision/artista
            let $art-nombre := data($artista/nombre/@text)
            let $art-img := data($artista/url-img/@src)
            where data($artista/@cod-artista) = $art-cod
            return <li>
                    <p> {concat($art-pais, " - ", $art-nombre , " - ","<i> ", $art-cancion, " </i>")}</p>
                    <p> {data($artista/descripcion/@text)} </p>
                    { if($art-img!="") then (<img src="{$art-img}"/>) else() }
                    <p><b>Recibió votos de: </b> {fn:string-join(for $pais in $interpretacion/voto/@emisor return data($pais), ", ")} </p>
                </li>
        }</ol>
    }
    </body>