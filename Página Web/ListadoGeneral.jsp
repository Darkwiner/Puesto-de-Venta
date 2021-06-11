<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id='listaViandas' scope='request' class='java.util.ArrayList' />


<html>
  <head>
    <title>
      Todas las viandas
    </title>
  </head>
  <body >

    <style>
      .button { background-color: #4CAF50; /* Green */ border: none; color: white; padding: 4px 15px; text-align:center;
      text-decoration: none; display: inline-block; font-size: 12px; margin: 2px 2px; transition-duration: 0.4s; cursor:
      pointer; } .button2 { background-color: white; color: black; border: 2px solid #008CBA; } .button2:hover
      {background-color: #008CBA; color: white; } table { width:100%; } table, th, td { border: 0px solid black;
      border-collapse: collapse; } th, td { padding: 10px; text-align: left; } #t01 tr:nth-child(even) {
      background-color: #eee; } #t01 tr:nth-child(odd) { background-color: #fff; } #t01 th { background-color: black;
      color: white; }
    </style>


    <style>
      body { background-image: url('imagenes/menu2.jpg'); background-repeat: no-repeat; background-attachment: fixed;
      background-size: 100% 100%; }
    </style>

    <style>

      * { box-sizing: border-box; } body { font-family: Segoe UI Light; } /* Style the header */ header { padding: 0px;
      text-align: center; font-size: 26px; color: white; background-color: rgba(30, 60, 80, .7); } /* Container for
      flexboxes */ section { display: -webkit-flex; display: flex; } /* Style the navigation menu */ nav { -webkit-flex:
      1; -ms-flex: 1; flex: 1; padding: 20px; } /* Style the list inside the menu */ nav ul { list-style-type: none;
      padding: 0; } /* Style the content */ article { -webkit-flex: 3; -ms-flex: 3; flex: 3; background-color: rgba(0,
      0, 0, .5); padding: 30px; } /* Style the footer */ footer { background-color: rgba(30, 60, 80, .5); padding: 5px;
      text-align: center; color: white; } /* Responsive layout - makes the menu and the content (inside the section) sit
      on top of each other instead of next to each other */ @media (max-width: 600px) { section {
      -webkit-flex-direction: column; flex-direction: column; } }
    </style>


    <header>
      <a style="color:white; font-family: Segoe UI Light;" >
        Listado de viandas
      </a>
    </header>

    <section>
      <nav>

        <form action='ListadoGeneralViandas' method='POST'>
          <p>
            <input type='submit' value='Consultar'  class="button button2" >
          </p>
        </form>

        <article>
          <table style="width:20%" border-collapse: "collapse";>
            <table id="t01">
              <tr>
                <th>
                  Código
                </th>
                <th>
                  Descripción
                </th>
                <th>
                  Precio Unitario
                </th>
                <th>
                  Tipo
                </th>
              </tr>
              <c:forEach items="${listaViandas}" var="i" >
                <tr>
                  <td>
                    ${i.codigoAlfanumerico}
                  </td>
                  <td>
                    ${i.descripcion}
                  </td>
                  <td>
                    ${i.precioUnitario}
                  </td>
                  <td>
                    ${i.esVege}
                  </td>
                </tr>
              </c:forEach>
            </table>
          </table>
        </article>
      </nav>
    </section>

    <nav>
      <footer>
        <div style="text-align: right">
          <p>
            <a href='Home.jsp'>
              <button class="button button2">
                Volver
              </button>
            </a>
          </p>
        </div>
      </footer>
    </nav>

  </body>
</html>
