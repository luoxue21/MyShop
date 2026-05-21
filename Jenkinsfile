pipeline {
    agent any
    
    tools {
        maven 'Maven-3.8.1'  // 在 Jenkins 全局工具配置中配置的名称
        jdk 'JDK-17'         // 在 Jenkins 全局工具配置中配置的名称
    }
    
    environment {
        // MySQL 配置（修改成你的实际密码）
        MYSQL_ROOT_PASSWORD = '123456'
        DB_NAME = 'shopping_db'
        // SpringBoot 运行端口
        SERVER_PORT = '8081'
    }
    
    stages {
        
        // 阶段1：代码拉取
        stage('Checkout') {
            steps {
                echo '拉取代码...'
                git url: 'https://github.com/Luoxue21/MyShop.git', branch: 'main'
            }
        }
        
        // 阶段2：数据库初始化（可选，如果数据库表已存在则跳过）
      
        
        // 阶段3：Maven 编译
        stage('Maven Build') {
            steps {
                echo '编译项目...'
                bat 'mvn clean compile -DskipTests'
            }
        }
        
        // 阶段4：运行单元测试
        stage('Run Tests') {
            steps {
                echo '运行测试用例...'
                bat 'mvn test'
            }
            post {
                always {
                    // 收集测试报告
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        // 阶段5：打包 JAR
        stage('Package') {
            steps {
                echo '打包项目...'
                bat 'mvn package -DskipTests'
            }
            post {
                success {
                    // 归档 JAR 文件
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
        
        // 阶段6：停止旧进程（可选，先查杀占用端口的进程）
        stage('Stop Old Application') {
            steps {
                echo '停止旧的应用进程...'
                bat '''
                    for /f "tokens=5" %%a in ('netstat -ano ^| findstr :%SERVER_PORT% ^| findstr LISTENING') do (
                        taskkill /F /PID %%a 2>nul
                    )
                    echo 旧进程已清理
                '''
            }
        }
        
        // 阶段7：启动新服务
        stage('Start Application') {
            steps {
                echo '启动 SpringBoot 应用...'
                bat '''
                    cd target
                    start /b java -jar *.jar --server.port=%SERVER_PORT% > app.log 2>&1
                    echo 应用已启动，访问地址: http://localhost:%SERVER_PORT%/login.html
                '''
            }
        }
        
        // 阶段8：健康检查（等待应用启动）
        stage('Health Check') {
            steps {
                echo '等待应用启动...'
                bat '''
                    set RETRY=0
                    :loop
                    curl -s http://localhost:%SERVER_PORT%/login.html >nul
                    if %errorlevel% neq 0 (
                        set /a RETRY+=1
                        if !RETRY! geq 30 (
                            echo 应用启动超时！
                            exit 1
                        )
                        echo 等待中... !RETRY!
                        timeout /t 2 >nul
                        goto loop
                    )
                    echo 应用启动成功！
                '''
            }
        }
    }
    
    post {
        // 构建完成后无论成功失败都显示消息
        always {
            echo '构建流程结束'
        }
        success {
            echo '🎉 构建成功！应用已部署到 http://localhost:8081/login.html'
            // 可选：发送邮件通知
            // emailext subject: "构建成功: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
            //          body: "构建成功，应用地址: http://localhost:8081/login.html",
            //          to: "admin@example.com"
        }
        failure {
            echo '❌ 构建失败，请检查日志'
        }
    }
}