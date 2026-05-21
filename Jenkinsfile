pipeline {
    agent any
    
    tools {
        maven 'Maven-3.8.1'
        jdk 'JDK-17'
    }
    
    environment {
        MYSQL_ROOT_PASSWORD = '123456'
        DB_NAME = 'shopping_db'
        SERVER_PORT = '8081'
    }
    
    stages {
        
        stage('Checkout') {
            steps {
                echo '拉取代码...'
                git url: 'https://github.com/Luoxue21/MyShop.git', branch: 'main'
            }
        }
        
        stage('Maven Build') {
            steps {
                echo '编译项目...'
                bat 'mvn clean compile -DskipTests'
            }
        }
        
        stage('Run Tests') {
            steps {
                echo '运行测试用例...'
                bat 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                echo '打包项目...'
                bat 'mvn package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
        
        stage('Stop Old Application') {
            steps {
                echo '停止旧的应用进程...'
                bat '''
                    @echo off
                    for /f "tokens=5" %%a in ('netstat -ano ^| findstr :%SERVER_PORT% ^| findstr LISTENING') do (
                        taskkill /F /PID %%a 2>nul
                    )
                    echo 旧进程已清理
                    exit /b 0
                '''
            }
        }
        
        stage('Start Application') {
            steps {
                echo '启动 SpringBoot 应用...'
                bat '''
                    cd target
                    start /b java -jar *.jar --server.port=%SERVER_PORT% > app.log 2>&1
                    echo 应用已启动，访问地址: http://localhost:%SERVER_PORT%/login.html
                    exit /b 0
                '''
            }
        }
        
        stage('Health Check') {
            steps {
                echo '等待应用启动...'
                bat '''
                    setlocal enabledelayedexpansion
                    set RETRY=0
                    :loop
                    timeout /t 2 /nobreak >nul
                    curl -s http://localhost:%SERVER_PORT%/login.html >nul 2>&1
                    if !errorlevel! neq 0 (
                        set /a RETRY+=1
                        if !RETRY! geq 15 (
                            echo 应用启动超时！
                            exit /b 1
                        )
                        echo 等待中... !RETRY!
                        goto loop
                    )
                    echo 应用启动成功！
                    exit /b 0
                '''
            }
        }
    }
    
    post {
        always {
            echo '构建流程结束'
        }
        success {
            echo '🎉 构建成功！应用已部署到 http://localhost:8081/login.html'
        }
        failure {
            echo '❌ 构建失败，请检查日志'
        }
    }
}