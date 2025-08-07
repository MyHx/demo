pipeline {
    
    agent any

    environment {
        // 这里主要是配置整个流水线所需要的变量，核心思想是要复用流水线和部署脚本的代码而只更改参数
        ENV = 'dev'

        // 部署目标机器或集群的IP&PORT
        TARGET_SSH_IP = '172.28.233.43'
        TARGET_SSH_PORT = '22'
        TARGET_SSH_USER = 'root'
    }

    
    stages {
        stage('Checkout') {
            steps {
                // 从Git仓库检出代码
                git branch: 'main', url: 'https://gitee.com/hexianmayun/demo.git'
            }
        }
        
        stage('Build') {
            steps {
                // 构建整个多模块项目
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                // 运行所有测试
                sh 'mvn test'
            }
            post {
                // 保存测试结果
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                // 打包项目
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('Deploy') {
            steps {
                // 部署到Maven仓库（如有需要）
                // sh 'mvn deploy -DskipTests'
                echo '部署阶段可根据实际需求配置'
            }
        }
    }
    
    post {
        // 清理工作空间
        always {
            cleanWs()
        }
        // 构建成功通知
        success {
            echo '构建成功！'
            // 可添加邮件通知等
        }
        // 构建失败通知
        failure {
            echo '构建失败！'
            // 可添加邮件通知等
        }
    }
}