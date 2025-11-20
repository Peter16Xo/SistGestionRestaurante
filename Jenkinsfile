pipeline {
    agent any

    // AHORA ESTA PARTE DEBE ESTAR ACTIVA (SIN // AL INICIO)
    tools {
        ant 'Ant' 
    }

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
