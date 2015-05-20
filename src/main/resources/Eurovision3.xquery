xquery version "3.0";
declare variable $anyo as xs:integer external;

let $ed := doc("Eurovision.xml")/eurovision/edicion[data(@anno) = $anyo]
return  <body>
    <h1>{concat(data($ed/@pais), " (", data($ed/@ciudad), ")")}</h1>
    <ol>{
        for $interpretacion in $ed//interprete-pais
        let $artista := doc("Eurovision.xml")/eurovision/artista[data(@cod-artista) = data($interpretacion/@artista)]
        return <li>
                <p> {concat(data($interpretacion/@pais), " - ", data($artista/@nombre) , " - ","<i> ", data($interpretacion/@cancion), " </i>")}</p>
                { if(data($artista/@descripcion)!="") then ( <p> { data($artista/@descripcion)}</p> ) else() }
                { if(data($artista/@url-img)!="") then (<img src="{data($artista/@url-img)}"/>) else() }
                <p><b>Recibió votos de: </b> {fn:string-join(for $pais in $interpretacion/voto/@emisor return data($pais), ", ")} </p>
            </li>
        }</ol>
    </body>