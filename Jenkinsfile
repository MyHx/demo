pipeline {
    agent any
    
    tools {
        jdk 'my_jdk'
        maven 'my_maven'
    }
    
    environment {
        // 环境配置
        ENV = 'dev'
        PROJECT_NAME = 'demo'
        
        // 部署配置
        TARGET_SSH_IP = '172.28.233.43'
        TARGET_SSH_PORT = '22'
        TARGET_SSH_USER = 'root'
        DEPLOY_PATH = '/opt/apps/demo'
        JAR_NAME = 'base-1.0.0.jar'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build & Test') {
            steps {
                script {
                    try {
                        // 清理并编译
                        sh 'mvn clean compile -q'
                        echo '编译完成'
                        
                        // 运行测试
                        sh 'mvn test -q'
                        echo '测试完成'
                    } catch (Exception e) {
                        error "构建或测试失败: ${e.getMessage()}"
                    }
                }
            }
            post {
                always {
                    // 保存测试报告
                    junit '**/target/surefire-reports/*.xml'
                    // 保存测试覆盖率报告
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'base/target/site/jacoco',
                        reportFiles: 'index.html',
                        reportName: '测试覆盖率报告'
                    ])
                }
            }
        }
        
        stage('Package') {
            steps {
                script {
                    try {
                        // 跳过测试进行打包
                        sh 'mvn package -DskipTests -q'
                        echo '打包完成'
                    } catch (Exception e) {
                        error "打包失败: ${e.getMessage()}"
                    }
                }
            }
        }
        
        stage('Deploy') {
            when {
                branch 'master'
            }
            steps {
                script {
                    try {
                        // 创建部署脚本
                        def deployScript = """
                            #!/bin/bash
                            cd ${DEPLOY_PATH}
                            
                            # 备份当前版本
                            if [ -f ${JAR_NAME} ]; then
                                cp ${JAR_NAME} ${JAR_NAME}.backup.\$(date +%Y%m%d_%H%M%S)
                            fi
                            
                            # 停止当前服务
                            pkill -f ${JAR_NAME} || true
                            sleep 5
                            
                            # 复制新版本
                            cp /tmp/${JAR_NAME} .
                            chmod +x ${JAR_NAME}
                            
                            # 启动服务
                            nohup java -jar ${JAR_NAME} --spring.profiles.active=${ENV} > app.log 2>&1 &
                            
                            echo "部署完成，服务已启动"
                        """
                        
                        // 将JAR文件传输到目标服务器
                        sh "scp -P ${TARGET_SSH_PORT} base/target/${JAR_NAME} ${TARGET_SSH_USER}@${TARGET_SSH_IP}:/tmp/"
                        
                        // 执行部署脚本
                        sh "ssh -p ${TARGET_SSH_PORT} ${TARGET_SSH_USER}@${TARGET_SSH_IP} '${deployScript}'"
                        
                        echo "部署成功完成"
                    } catch (Exception e) {
                        error "部署失败: ${e.getMessage()}"
                    }
                }
            }
        }
    }
    
    post {
        always {
            // 清理工作空间
            cleanWs()
        }
        success {
            script {
                if (env.BRANCH_NAME == 'master') {
                    echo "✅ 构建和部署成功完成！"
                    // 可以添加钉钉、企业微信等通知
                } else {
                    echo "✅ 构建成功完成！"
                }
            }
        }
        failure {
            script {
                echo "❌ 构建失败！"
                // 可以添加失败通知
            }
        }
        unstable {
            echo "⚠️ 构建不稳定，请检查测试结果"
        }
    }
}