<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to TodoAI</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f0f2f5;
        }
        .container {
            text-align: center;
            padding: 40px;
            background: white;
            border-radius: 12px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #1a73e8;
            margin-bottom: 30px;
        }
        .buttons {
            display: flex;
            gap: 20px;
            justify-content: center;
        }
        a {
            text-decoration: none;
            padding: 12px 24px;
            border-radius: 6px;
            font-weight: 500;
            transition: background-color 0.3s;
        }
        .login {
            background-color: #1a73e8;
            color: white;
        }
        .register {
            background-color: #34a853;
            color: white;
        }
        a:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Welcome to To-Do List with AI</h2>
        <div class="buttons">
            <a href="login.jsp" class="login">Login</a>
            <a href="register.jsp" class="register">Register</a>
        </div>
    </div>
</body>
</html>