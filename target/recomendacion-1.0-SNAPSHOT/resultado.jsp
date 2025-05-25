<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Curso Recomendado</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f4f7fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: white;
            padding: 30px 40px;
            border-radius: 8px;
            box-shadow: 0 8px 16px rgba(0,0,0,0.15);
            width: 350px;
            text-align: center;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        p {
            font-size: 18px;
            color: #444;
        }
        strong {
            color: #007bff;
        }
        a {
            display: inline-block;
            margin-top: 25px;
            text-decoration: none;
            background-color: #007bff;
            color: white;
            padding: 10px 22px;
            border-radius: 5px;
            font-weight: 600;
            transition: background-color 0.3s ease;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Resultado de la recomendación</h2>
        <p>El curso recomendado para ti es: <strong>${resultado}</strong></p>
        <a href="index.jsp">Volver</a>
    </div>
</body>
</html>
