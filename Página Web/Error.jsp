<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id='msgError' scope='request' class='java.lang.String' />

<html>
  <head>
    <title>
      Algo salio mal
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

      * { box-sizing: border-box; } body { font-family: Arial, Helvetica, sans-serif; } /* Style the header */ header {
      padding: 0px; text-align: center; font-size: 15px; } /* Create two columns/boxes that floats next to each other */
      nav { float: left; width: 30%; height: 300px; /* only for demonstration, should be removed */ background: #ccc;
      padding: 20px; } /* Style the list inside the menu */ nav ul { list-style-type: none; padding: 0; } article {
      float: left; padding: 20px; width: 70%; background-color: #f1f1f1; height: 300px; /* only for demonstration,
      should be removed */ } /* Clear floats after the columns */ section::after { content: ""; display: table; clear:
      both; } /* Style the footer */ footer { padding: 0px; text-align: center; color: white; } /* Responsive layout -
      makes the two columns/boxes stack on top of each other instead of next to each other, on small screens */ @media
      (max-width: 600px) { nav, article { width: 100%; height: auto; } }
    </style>

    <header>
      <h1  style="background-color:#306080;" align="center" style="font-size:3vw;">
        <a style="color:white; font-family: Segoe UI Light;" >
          Ups!
        </a>
      </h1>
    </header>


    <section>
      <style>
        body { background-image: url('imagenes/error.png'); background-repeat: no-repeat; background-attachment: fixed;
        background-size: 100% 100%; }
      </style>
      <div style="text-align: center">
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <h3>
          ${msgError}
        </h3>
      </div>
    </section>

    <footer>
      <div style="text-align: center">
        <p>
          <a href='Home.jsp'>
            <button class="button button2">
              Volver
            </button>
          </a>
        </p>
      </div>
    </footer>


  </body>
</html>
