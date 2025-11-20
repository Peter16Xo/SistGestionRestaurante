pipeline {
    agent any
    
    // Esta sección es opcional, actívala si Jenkins no encuentra el comando 'ant'
    // tools {
    //     ant 'Ant' // Asegúrate de tener configurado 'Ant' en Jenkins -> Global Tool Configuration
    // }

    stages {
        stage('Limpieza Inicial') {
            steps {
                echo 'Limpiando proyecto...'
                script {
                    if (isUnix()) {
                        sh 'ant clean'
                    } else {
                        bat 'ant clean'
                    }
                }
            }
        }
        stage('Build (Compilación)') {
            steps {
                echo 'Compilando y generando JAR...'
                script {
                    if (isUnix()) {
                        sh 'ant jar'
                    } else {
                        bat 'ant jar'
                    }
                }
            }
        }
        stage('Test (Pruebas Unitarias)') {
            steps {
                echo 'Ejecutando JUnit Tests...'
                // Si alguna prueba falla, el pipeline se detendrá aquí
                script {
                    if (isUnix()) {
                        sh 'ant test'
                    } else {
                        bat 'ant test'
                    }
                }
            }
        }
    }
}
