<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>
      Viandas
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
  <body >

    <style>



      * { box-sizing: border-box; } body { font-family: Arial, Helvetica, sans-serif; } /* Style the header */ header {
      padding: 0px; text-align: center; font-size: 15px; } /* Create two columns/boxes that floats next to each other */
      nav { float: left; width: 30%; height: 300px; /* only for demonstration, should be removed */ padding: 20px; } /*
      Style the list inside the menu */ nav ul { list-style-type: none; padding: 0; } article { float: left; padding:
      20px; width: 70%; height: 300px; /* only for demonstration, should be removed */ } /* Clear floats after the
      columns */ section::after { content: ""; display: table; clear: both; } /* Style the footer */ footer { padding:
      0px; text-align: center; color: white; } /* Responsive layout - makes the two columns/boxes stack on top of each
      other instead of next to each other, on small screens */ @media (max-width: 600px) { nav, article { width: 100%;
      height: auto; } }
    </style>

    <style>
      body { background-image: url('imagenes/menu2.jpg'); background-repeat: no-repeat; background-attachment: fixed;
      background-size: 100% 100%; }
    </style>

    <header>
      <h1  style="background-color:#306080;" align="center" style="font-size:3vw;">
        <a style="color:white; font-family: Segoe UI Light;" >
          Bienvenido a Viandas
        </a>
      </h1>
    </header>


    <section>
      <nav>
        <table style="width:20%" border-collapse: "collapse";>
          <tr>
          </tr>
          <tr>
            <td>
              <br>
              <h1>
                <A href='ListadoGeneral.jsp'>
                  <img src="imagenes/logoM3.png" alt="Lista">
                </A>
              </h1>
            </td>
            <td>
              <br>
              <br>
              <h3>
                Todas las viandas
              </h3>
            </td>
          </tr>
          <tr>
            <td>
              <br>
              <br>
              <h1>
                <A href='ListadoPorDescripcion.jsp'>
                  <img src="imagenes/libro1.png" alt="Lista" >
                </A>
              </h1>
            </td>
            <td>
              <br>
              <br>
              <h3>
                Buscar por descripción
              </h3>
            </td>
          </tr>
          <tr>
            <td>
              <br>
              <br>
              <h1>
                <a href='ViandaDetallada.jsp'>
                  <img src="imagenes/libro2.png" alt="Lista" >
                </a>
              </h1>
            </td>
            <td>
              <br>
              <br>
              <h3>
                Buscar por código
              </h3>
            </td>
          </tr>
        </table>
      </nav>
      <article>
      </article>
    </section>


  </body>
</html>
