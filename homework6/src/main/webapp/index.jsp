<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>文件管理系统</title>
    <!-- React 依赖 -->
    <script src="https://unpkg.com/react@18/umd/react.development.js"></script>
    <script src="https://unpkg.com/react-dom@18/umd/react-dom.development.js"></script>
    
    <!-- Babel -->
    <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
    
    <!-- Material-UI -->
    <script src="https://unpkg.com/@mui/material@5.15.3/umd/material-ui.development.js"></script>
    
    <!-- Roboto 字体 -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" />
    <!-- Material Icons -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    
    <!-- Emotion -->
    <script src="https://unpkg.com/@emotion/react@11.11.3/dist/emotion-react.umd.min.js"></script>
    <script src="https://unpkg.com/@emotion/styled@11.11.0/dist/emotion-styled.umd.min.js"></script>

    <style>
        body { margin: 0; }
    </style>
</head>
<body>
    <div id="root"></div>

    <script type="text/babel">
        const { 
            AppBar, 
            Toolbar, 
            Typography, 
            Button, 
            Box 
        } = MaterialUI;

        function App() {
            return (
                <Box sx={{ flexGrow: 1 }}>
                    <AppBar position="static">
                        <Toolbar>
                            <Typography
                                variant="h6"
                                component="div"
                                sx={{ flexGrow: 1 }}
                            >
                                文件管理系统
                            </Typography>
                            <Button color="inherit">登录</Button>
                            <Button color="inherit">注册</Button>
                        </Toolbar>
                    </AppBar>
                </Box>
            );
        }

        const root = ReactDOM.createRoot(document.getElementById('root'));
        root.render(<App />);
    </script>
</body>
</html>
